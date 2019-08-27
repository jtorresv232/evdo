import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-confirmacion',
  templateUrl: './confirmacion.component.html',
  styleUrls: ['./confirmacion.component.css']
})
export class ConfirmacionComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ConfirmacionComponent>) { }

  ngOnInit() {
  }

  public closeDialog() {
    const data = {
      confirmar: true
    };
    this.dialogRef.close(data);
  }

  public cancel() {

    this.dialogRef.close();
  }

}
