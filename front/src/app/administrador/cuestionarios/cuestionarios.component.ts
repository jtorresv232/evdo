import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {HttpService} from '../../comun/http.service';
import { MyFilterPipe, MyFilterPipe2, MyFilterName, MyFilterCategory } from '../../comun/mypipe';
import {MatSnackBar} from '@angular/material';
import 'rxjs/Rx';
import {MatDialog, MatDialogRef} from '@angular/material';
import { Router, NavigationExtras} from '@angular/router';
import {DataService} from '../../dataservice';
import { DialogAddC } from '../pop-up-add-cuestionario/pop-up-add-cuestionario.component';
import {PageEvent} from '@angular/material';



@Component({
  selector: 'app-cuestionarios',
  templateUrl: './cuestionarios.component.html',
  styleUrls: ['./cuestionarios.component.css']
})
export class CuestionariosComponent implements OnInit {

  public dialogRef: MatDialogRef<DialogAddC>;
  public cuestionarios: any = [];
  public panelOpenState = false;
  public qDone = false;
  public preguntas: any = [];
  public displayPreguntas: any = ['codigo', 'texto'];
  public totalPreguntas: any = [];
  public categorias: any = [];
  public categoria: any = '';
  public mycuest: any = '';
  public mypregunta: any = '';
  public pageSizeOptions;
  public leng: any = 0;
  public facultad;

  constructor(private _service: HttpService, public dialog: MatDialog,
    private data: DataService, private cdr: ChangeDetectorRef, private _router: Router) { }

  pageEvent: PageEvent;

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }

  ngOnInit() {
    this.pageEvent = {pageIndex: 0, pageSize: 5, length: this.cuestionarios.length};
    this.data.changeMessage('admin');

    this._service.getCuestionarios().map(response => response)
    .subscribe(res => {
      this.cuestionarios = res;
      this.leng = this.cuestionarios.length;
    }, err => {
      console.log(err);
    });

    this._service.getCategorias().map(response => response)
    .subscribe(res => {
      this.categorias = res;
    }, err => {
      console.log(err);
    });

    this._service.getPreguntas().map(response => response)
    .subscribe(res => {
      this.totalPreguntas = [];
      this.totalPreguntas = res;
    }, err => {
      console.log(err);
      
    });
  }

  public getPreguntas(identificacion) {
    this.qDone = false;
    this.preguntas = [];
    this._service.getPxC(identificacion).subscribe(data => {
        
          this.preguntas = data;
          this.qDone = true;
        }, err => {
          this.preguntas = [];
        });
  }

  public agregar(mycuest) {

    const datos = {
      'pregunta': this.mypregunta,
      'cuestionario': this.mycuest
    };

    this._service.addPxC(datos).subscribe(data => {
          this.getPreguntas(mycuest);
        this.cdr.detectChanges();
        }, err => {
        });
  }

  irEditar(cuest) {
    this._router.navigate(['/admin/editar-encuesta', cuest.identificacion]);
    console.log(cuest);
  }

  irCrearCuestionario() {
    this._router.navigate(['/admin/crear-encuesta']);
  }

  public openDialog() {
    this.dialogRef = this.dialog.open(DialogAddC);
    this.dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (result.nombre) {
          const datos = {
            nombre: result.nombre,
            estudiante: true
          };
        this._service.addCuestionario(datos).subscribe(data => {
              this.cuestionarios.push(data);
            }, err => {
            });
        } else {
          console.log('no hay nada que guardar');
        }
      }
    });
  }

}
