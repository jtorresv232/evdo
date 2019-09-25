import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { FormGroup, FormBuilder, Validators, RequiredValidator } from '@angular/forms';
import { MatDialogRef, MatDialog } from '@angular/material';
import { PopUpAgregarUsuarioComponent } from '../pop-up-agregar-usuario/pop-up-agregar-usuario.component';
import { ConfirmacionComponent } from '../confirmacion/confirmacion.component';

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent implements OnInit {

  public usuarios: any[] = [];
  public facultades: any[] = [];
  public dialogUsuarios: MatDialogRef<PopUpAgregarUsuarioComponent>;
  public dialogRefConf: MatDialogRef<ConfirmacionComponent>;
  public usuario: any;
  public uDone = false;
  public administrador = false;

  constructor(private _service: HttpService,  public dialog: MatDialog,
    public dialogRef: MatDialog) { }

  ngOnInit() {
    this._service.getUsuarios()
    .subscribe(respuesta => {
      this.usuarios = respuesta;
    });

    this._service.getFiltroUser()
    .map(res => res)
    .mergeMap(sesionUser => this._service.findUsuario(sesionUser.ccid))
    .subscribe(resp => {
      this.uDone = true;
      this.usuario = resp;
      if(this.usuario.rol === 'ADMINISTRADOR') {
        this.administrador = true;
      }
      console.log(resp);
    });

    this._service.getFacultades()
    .subscribe(resp => {
      this.facultades = resp;
    });
  }

  openDialog() {
    this.dialogUsuarios = this.dialog.open(PopUpAgregarUsuarioComponent);
    this.dialogUsuarios.afterClosed().subscribe(result => {
      this.usuarios.push(result);
    });
  }

  buscarFacultad(facultad) {
    const nombreFacultad = this.facultades.find(x => x.codigo === facultad).nombre;
    return nombreFacultad;
  }

  public openDialogConf(cedula) {
    this.dialogRefConf = this.dialog.open(ConfirmacionComponent);

    this.dialogRefConf.afterClosed().subscribe((result) => {
      if (result) {
        if (result.confirmar) {
          const pos = this.usuarios.indexOf(this.usuarios.find(x => x.cedula === cedula));
          this.usuarios.splice(pos, 1);
            this._service.deleteUsuario(cedula)
            .subscribe(x =>
              console.log(x)
              );
        } else {
          console.log('no hay nada que guardar');
        }
      }
    });
  }

}
