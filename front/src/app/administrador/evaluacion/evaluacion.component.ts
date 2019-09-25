import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { MatDialogRef, MatDialog } from '@angular/material';
import { PopUpAddEvaluacionComponent } from '../pop-up-add-evaluacion/pop-up-add-evaluacion.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-evaluacion',
  templateUrl: './evaluacion.component.html',
  styleUrls: ['./evaluacion.component.css']
})
export class EvaluacionComponent implements OnInit {
  public evaluaciones;
  public dialogRef: MatDialogRef<PopUpAddEvaluacionComponent>;

  constructor(private _service: HttpService, public dialog: MatDialog) { }

  ngOnInit() {
    this._service.getEvaluaciones().subscribe(resp => {
      this.evaluaciones = resp;
      console.log(this.evaluaciones.filter(x => x.cargado === false).length);
      if(this.evaluaciones.filter(x => x.cargado === false).length > 0) {
        Observable.interval(10000).takeWhile(() => true).subscribe(() => this.reloadEvaluaciones());
      }
    });
  }

  reloadEvaluaciones() {
    this._service.getEvaluaciones().subscribe(response => {
      this.evaluaciones = response;
    });
  }

  openDialog() {
    this.dialogRef = this.dialog.open(PopUpAddEvaluacionComponent,{ });
    this.dialogRef.afterClosed().subscribe(result => {
      if(result) {
        this._service.getEvaluacion(result).subscribe(res => {
          console.log(res);
          if(res) {
            console.log('existo');
          }
        }, error => {
          this._service.addEvaluacion({codigo: 'eval'+result, porcentaje:0, cargado:false, semestre:result, porcentajeprofesor: 0})
            .subscribe(resp=>{
              this.evaluaciones.push(resp);
              this._service.poblarCursos(result);
            });
        });
      }
    });
  }

}
