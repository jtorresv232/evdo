import { Component, OnInit, Inject } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {MAT_DIALOG_DATA, ErrorStateMatcher} from '@angular/material';
import { FormBuilder, FormGroup, Validators, FormArray, FormControl, FormGroupDirective, NgForm } from '@angular/forms';

@Component({
  selector: 'app-pop-up-edit-asignacion',
  templateUrl: './pop-up-edit-asignacion.component.html',
  styleUrls: ['./pop-up-edit-asignacion.component.css']
})
// tslint:disable-next-line:component-class-suffix
export class DialogEditAsig implements OnInit {

  public fechaInicial: any;
  public fechaFinal: any;
  public cuestionarios: any = [];
  public cuestionario: any;
  public startDate = new Date(2018, 0, 1);
  public editForm: FormGroup;
  public editProf: FormGroup;
  public datos = {};
  matcher = new MyErrorStateMatcher();
  public mindate = new Date();


  constructor(public dialogRef: MatDialogRef<DialogEditAsig>
    , @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.mindate.setDate(this.mindate.getDate() + 1)
    this.cuestionarios = this.data;

    this.editForm = new FormBuilder().group({
      encuesta: ['', [Validators.required]],
      fecha_inicio: ['', [Validators.required]],
      fecha_final: ['', [Validators.required]]
    }, {validator: this.checkDates });

    this.editProf = new FormBuilder().group({
      encuesta: ['', [Validators.required]],
      fecha_inicio: ['', [Validators.required]],
      fecha_final: ['', [Validators.required]]
    }, {validator: this.checkDates});
  }

  public getFechaI(event) {
    this.fechaInicial = event;
  }

  public closeDialog(form_data) {
    const data = this.datos;
    this.dialogRef.close(data);
  }

  public cancel() {
    this.dialogRef.close();
  }

  agregar(form_data: FormGroup) {
    if (form_data.valid) {
      const data = {
        fecha_enc_final: form_data.get('fecha_final').value,
        fecha_enc_inicio: form_data.get('fecha_inicio').value,
        encuesta: form_data.get('encuesta').value
      };
      this.datos['profesor'] = data;
      form_data.disable();
    console.log(this.datos);
    }
  }

  agregarEstudiantes (form_data: FormGroup) {
    if (form_data.valid) {
      const data = {
        fecha_enc_final: form_data.get('fecha_final').value,
        fecha_enc_inicio: form_data.get('fecha_inicio').value,
        encuesta: form_data.get('encuesta').value
      };
      this.datos['estudiantes'] = data;
      form_data.disable();
    console.log(this.datos);
    }
  }

  checkDates(group: FormGroup) {
    console.log(group.errors);
      let inicial = group.controls.fecha_inicio.value;
      let final = group.controls.fecha_final.value;
      if (group.controls.fecha_inicio.dirty && group.controls.fecha_final.dirty) {
        console.log('heyyy');
        return (inicial <= final)? null : {notSame: true};
      }
    return null;
  }

}

// MATCHER CLASS //

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const invalidCtrl = !!(control && control.invalid && control.touched);
    const invalidParent = !!(control && control.parent && control.parent.hasError('notSame') && control.parent.invalid);

    return (invalidCtrl || invalidParent);
  }
}
