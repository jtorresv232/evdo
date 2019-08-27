import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { CommonModule } from '@angular/common';
import {ComunModule} from '../comun/comun.module';
import { EvaluarCursoEstudianteRoutingModule, routingComponents } from './evaluar-curso-estudiante-routing.module';
import { EvaluarComponent } from './evaluar/evaluar.component';
import { EvaluarCursosComponent } from './evaluar-cursos/evaluar-cursos.component';
import { Dialog } from './popup-encuesta/popup-encuesta.component';
import { FormularioComponent } from './formulario/formulario.component';


@NgModule({
  imports: [
    CommonModule,
    ComunModule,
    EvaluarCursoEstudianteRoutingModule,
    BrowserModule

  ],
  declarations: [
    EvaluarComponent,
    EvaluarCursosComponent,
    Dialog,
    routingComponents,
    FormularioComponent
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  entryComponents: [Dialog],
})
export class EvaluarCursoEstudianteModule { }
