import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpService } from '../../comun/http.service';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-pop-up-agregar-usuario',
  templateUrl: './pop-up-agregar-usuario.component.html',
  styleUrls: ['./pop-up-agregar-usuario.component.css']
})
export class PopUpAgregarUsuarioComponent implements OnInit {

  public usuariosForm: FormGroup;
  public facultades: any[] = [];

  constructor(private _service: HttpService, public dialogRef: MatDialogRef<PopUpAgregarUsuarioComponent>) { }

  ngOnInit() {
    this._service.getFacultades()
    .subscribe(resp => {
      this.facultades = resp;
    });

    this.usuariosForm = new FormBuilder().group({
      nombre: ['', Validators.required],
      cedula: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      rol: ['', Validators.required],
      telefono: ['', Validators.required],
      direccion: ['', Validators.required],
      facultad: [ , Validators.required]
    });
  }

  guardarUsuario(form) {
    const data = {
      nombre: form.get('nombre').value,
      cedula: form.get('cedula').value,
      correo: form.get('correo').value,
      rol: form.get('rol').value,
      telefono: form.get('telefono').value,
      direccion: form.get('direccion').value,
      facultad: parseInt(form.get('facultad').value, 10)
    };
    console.log(data);
    this._service.addUsuario(data)
    .subscribe(resp => {
      console.log(resp);
      this.dialogRef.close(resp);
    }, err => {
      console.log(err);
    }
    );
  }

  cancelar() {
    this.dialogRef.close();
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

}
