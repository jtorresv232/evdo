import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { MatDialog, MatDialogRef } from '@angular/material';
import { PopUpAgregarTemaComponent } from '../pop-up-agregar-tema/pop-up-agregar-tema.component';

@Component({
  selector: 'app-temas',
  templateUrl: './temas.component.html',
  styleUrls: ['./temas.component.css']
})
export class TemasComponent implements OnInit {

  constructor(private _service: HttpService, public dialog: MatDialog) { }
  public temas: any = [];
  public tDone = false;
  public panelOpenState = false;
  public rxt: any = [];
  public rxtFiltered: any = [];
  public dialogRef: MatDialogRef<PopUpAgregarTemaComponent>;

  ngOnInit() {
    this._service.getTemas_x_rol('MOIS_ANALISTAENCUESTAS')
    .map(response => response)
    .subscribe(response => {
      this.temas = response;
      this.tDone = true;
    });

    this._service.getRXT()
    .map(response => response)
    .subscribe(response => {
      this.rxt = response;
    });
  }

  filterTemas(tCodigo) {
    console.log(this.rxt);
    this.rxtFiltered = this.rxt.filter(rx => rx.tema === tCodigo);
  }

  openDialogAddTema() {
    this.dialogRef = this.dialog.open(PopUpAgregarTemaComponent, {
      minWidth: '400px'
    });
    this.dialogRef.afterClosed().subscribe((result) => {
      if(result) {
        location.reload();
      }
    });
  }

}
