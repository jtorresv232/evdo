import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef} from '@angular/material/dialog';
import {MAT_DIALOG_DATA} from '@angular/material';
import { HttpService } from '../../comun/http.service';

@Component({
  selector: 'app-opciones',
  templateUrl: './opciones.component.html',
  styleUrls: ['./opciones.component.css']
})
export class OpcionesComponent implements OnInit {
  public opciones: any = [];
  public derivadas: any = [];

  constructor(private _service: HttpService, public dialogRef: MatDialogRef<OpcionesComponent>
    , @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    console.log(this.data);
    this._service.getOpciones(this.data.numero).subscribe(res => {
      this.opciones = res;
    });
    this._service.getDerivadas(this.data.numero).subscribe(resp => {
      this.derivadas = resp;
    });
    console.log(this.opciones.length);
  }

  public closeDialog() {
    this.dialogRef.close();
  }

  public cancel() {
    this.dialogRef.close();
  }

}
