import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-pop-up-add-evaluacion',
  templateUrl: './pop-up-add-evaluacion.component.html',
  styleUrls: ['./pop-up-add-evaluacion.component.css']
})
export class PopUpAddEvaluacionComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<PopUpAddEvaluacionComponent>) { }
  public semestre;

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }

  confirmar() {
    this.dialogRef.close(this.semestre);
  }

}
