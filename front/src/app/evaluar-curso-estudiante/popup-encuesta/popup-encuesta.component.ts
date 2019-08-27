import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-popup-encuesta',
  templateUrl: './popup-encuesta.component.html',
  styleUrls: ['./popup-encuesta.component.css']
})
// tslint:disable-next-line:component-class-suffix
export class Dialog implements OnInit {

  public preguntas = ['La organización del curso ha sido', 'El nivel de los contenidos ha sido'
  , 'La utilidad de los contenidos aprendido', 'La utilización de casos prácticos',
  'En general, el curso te ha parecido'];


  constructor(public dialogRef: MatDialogRef<Dialog>) { }

  ngOnInit() {
  }

}
