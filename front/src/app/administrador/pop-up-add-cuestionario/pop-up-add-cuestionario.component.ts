import { Component, OnInit, Inject } from '@angular/core';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
@Component({
  selector: 'app-pop-up-add-cuestionario',
  templateUrl: './pop-up-add-cuestionario.component.html',
  styleUrls: ['./pop-up-add-cuestionario.component.css']
})
// tslint:disable-next-line:component-class-suffix
export class DialogAddC implements OnInit {

  public nombre: string;

  constructor(public dialogRef: MatDialogRef<DialogAddC>) { }

  ngOnInit() {
  }

  public closeDialog() {
    const data = {
      nombre: this.nombre
    };
    this.dialogRef.close(data);
  }

  public cancel() {

    this.dialogRef.close();
  }

}
