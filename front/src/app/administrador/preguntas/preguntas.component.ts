import { Component, OnInit, Output } from '@angular/core';
import {HttpService} from '../../comun/http.service';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import {MatDialog, MatDialogRef, MatSnackBar} from '@angular/material';
import { DialogAddP } from '../pop-up-add-pregunta/pop-up-add-pregunta.component';
import { OpcionesComponent } from '../opciones/opciones.component';
import {DataService} from '../../dataservice';
import { ConfirmacionComponent } from '../confirmacion/confirmacion.component';


@Component({
  selector: 'app-preguntas',
  templateUrl: './preguntas.component.html',
  styleUrls: ['./preguntas.component.css']
})
export class PreguntasComponent implements OnInit {
  public dialogRef: MatDialogRef<DialogAddP>;
  public dialogRefConf: MatDialogRef<ConfirmacionComponent>;
  public dialogOpciones: MatDialogRef<OpcionesComponent>;
  public message = 'Preguntas';
  @Output() messageEvent: any;
  public preguntas: any = [];
  public misPreguntas: any = [];
  public misCategorias: any = [];
  public pDone = false;
  public cDone = false;
  public filts = [];
  public vari = 'hola';
  public nombre = 'pregunta';
  public text;
  source: any;
  public cargado = false;

  constructor(private _service: HttpService, public dialog: MatDialog, private data: DataService,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.data.changeMessage('admin');
    this._service.getCategorias().map(response => response)
    .subscribe(res => {
      this.misCategorias = res;
      this.cDone = true;
    }, err => {
      console.log(err);
    });

    this._service.getPreguntas().map(response => response)
    .subscribe(res => {
      this.preguntas = res;
      this.misPreguntas = this.preguntas.slice(0, 10);
      this.cargado = true;
      this.pDone = true;
    }, err => {
      console.log(err);
    });
  }

  public delete(pregunta) {
    console.log(pregunta);
    const pos = this.misPreguntas.indexOf(this.misPreguntas.filter(i => {
        return i.numero === pregunta.numero;
      })[0]);
      this._service.deletePregunta(pregunta.numero).subscribe(res => {
        console.log(res);
        this.misPreguntas.splice(pos, 1);
        this.openSnackBar('Se ha eliminado correctamente la pregunta ' + pregunta.numero);
      }, err => {
        console.log(err);
        this.openErrorSnackBar('No se puede eliminar la pregunta ' + pregunta.numero + '. Esta siendo utilizada en una encuesta');
      });
  }

  public openDialog() {
    this.dialogRef = this.dialog.open(DialogAddP, {
      panelClass: 'addQuestions',
      data: this.misCategorias
    });
    this.dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.misPreguntas.push(result);
        location.reload();
        this.openSnackBar('Se ha agregado la pregunta (' + result.numero + ') correctamente');
        } else {
          console.log('no hay nada que guardar');
        }
    });
  }

  public openDialogOpciones(pregunta: any) {
    this.dialogOpciones = this.dialog.open(OpcionesComponent, {
      data: pregunta
    });
    this.dialogOpciones.afterClosed();
  }

  public openDialogConf(pregunta) {
    this.dialogRefConf = this.dialog.open(ConfirmacionComponent);
    this.dialogRefConf.afterClosed().subscribe((result) => {
      if (result) {
        if (result.confirmar) {
          this.delete(pregunta);
        } else {
          console.log('no hay nada que guardar');
        }
      }
    });
  }
/**
   * CHECKS IF ONE ELEMENT LIES BEFORE THE OTHER
  */
 isbefore(a, b) {
  if (a.parentNode === b.parentNode) {
    for (let cur = a; cur; cur = cur.previousSibling) {
      if (cur === b) {
        return true;
      }
    }
  }
  return false;
}
/**
 * LIST ITEM DRAP ENTERED
*/
dragenter($event) {
  if (this.isbefore(this.source, $event.currentTarget)) {
    $event.currentTarget.parentNode.insertBefore(this.source, $event.currentTarget); // insert before
  } else {
    $event.currentTarget.parentNode.insertBefore(this.source, $event.currentTarget.nextSibling); // insert after
  }
}
/**
 * LIST ITEM DRAG STARTED
 */
dragstart($event) {
  this.source = $event.currentTarget;
  $event.dataTransfer.effectAllowed = 'move';
}

openSnackBar(mensaje) {
  this.snackBar.open(mensaje, '', {
    duration: 1300,
    panelClass: 'green-snackbar'
  });
}

openErrorSnackBar(mensaje) {
  this.snackBar.open(mensaje, '', {
    duration: 3000,
    panelClass: 'red-snackbar'
  });
}

}
