import { Component, OnInit, Inject } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-pop-up-agregar-tema',
  templateUrl: './pop-up-agregar-tema.component.html',
  styleUrls: ['./pop-up-agregar-tema.component.css']
})
export class PopUpAgregarTemaComponent implements OnInit {

  public tema = '';

  constructor(private _service: HttpService, public dialogRef: MatDialogRef<PopUpAgregarTemaComponent>
    , @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
  }

  agregarTema() {
    this._service.addTema({tema: 0, descripcion: this.tema})
      .subscribe(resp => {
        this.dialogRef.close(true);
      });

  }

  public cancel() {
   this.dialogRef.close(false);
  }

}
