import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';

@Component({
  selector: 'app-unidades',
  templateUrl: './unidades.component.html',
  styleUrls: ['./unidades.component.css']
})
export class UnidadesComponent implements OnInit {

  constructor(private _service: HttpService) { }
  public temas: any = [];
  public unidades: any = [];
  public unidadesForm: FormGroup;
  public uLoaded = false;

  ngOnInit() {
    this.unidadesForm = new FormBuilder().group({
      emails: new FormBuilder().array([])
    });
    this._service.getTemas_x_rol('MOIS_ANALISTAENCUESTAS')
    .map(response => response)
    .subscribe(result => {
      this.temas = result;
    });

    this._service.getFacultades()
    .map(response => response)
    .subscribe(resp => {
      this.unidades = resp;
      this.unidades.forEach(element => {
        this.agregarEmail(element.email);
      });
      this.uLoaded = true;
    });
  }

  get emails(): FormArray {
    return this.unidadesForm.get('emails') as FormArray;
  }

  crearEmailsFormGroup(correo): FormGroup {
    let form;
    if (correo !== '' && correo != null) {
      form = new FormBuilder().group({
        email: [correo, Validators.email]
    });
    } else {
      form = new FormBuilder().group({
        email: ['', Validators.email]
    });
    }
    return form;
  }

  agregarEmail(correo) {
    this.emails.push(this.crearEmailsFormGroup(correo));
  }

  modificar(form, unidadCodigo) {
    if (form.valid) {
      const data = {
        email: form.get('email').value,
        codigo: unidadCodigo
      };
  
      this._service.editFacultad(data).map(response => response)
      .subscribe(res => {
        this.unidades.find(x => x.codigo === unidadCodigo)['email'] = form.get('email').value;
      });
    }
  }

}
