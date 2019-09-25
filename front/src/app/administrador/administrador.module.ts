import { NgModule, NO_ERRORS_SCHEMA, Injectable } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {ComunModule} from '../comun/comun.module';
import { AdministradorRoutingModule, routingComponents } from './administrador-routing.module';
import { AdminComponent } from './admin/admin.component';
import { PreguntasComponent } from './preguntas/preguntas.component';
import { AsignacionesComponent } from './asignaciones/asignaciones.component';
import { CuestionariosComponent } from './cuestionarios/cuestionarios.component';
import { DialogAddP } from './pop-up-add-pregunta/pop-up-add-pregunta.component';
import { DialogEditAsig } from './pop-up-edit-asignacion/pop-up-edit-asignacion.component';
import { DialogAddC } from './pop-up-add-cuestionario/pop-up-add-cuestionario.component';
import { ResultadosComponent } from './resultados/resultados.component';
import { OpcionesComponent } from './opciones/opciones.component';
import { CrearEncuestaComponent } from './crear-encuesta/crear-encuesta.component';
import { ComentariosComponent } from './comentarios/comentarios.component';
import { MentionModule } from 'angular-mentions/mention';
import { UnidadesComponent } from './unidades/unidades.component';
import { TemasComponent } from './temas/temas.component';
import { PuntosComponent } from './puntos/puntos.component';
import { ConfirmacionComponent } from './confirmacion/confirmacion.component';
import { EditarCuestionarioComponent } from './editar-cuestionario/editar-cuestionario.component';
import { AdministracionComponent } from './administracion/administracion.component';
import { PopUpAgregarUsuarioComponent } from './pop-up-agregar-usuario/pop-up-agregar-usuario.component';
import { ConfiguracionComponent } from './configuracion/configuracion.component';
import { FiltrosAsignacionesComponent } from './filtros-asignaciones/filtros-asignaciones.component';
import { DerivadasComponent } from './derivadas/derivadas.component';
import { PopUpAgregarTemaComponent } from './pop-up-agregar-tema/pop-up-agregar-tema.component';
import { EvaluacionComponent } from './evaluacion/evaluacion.component';
import { PopUpAddEvaluacionComponent } from './pop-up-add-evaluacion/pop-up-add-evaluacion.component';


@NgModule({
  imports: [
    CommonModule,
    AdministradorRoutingModule,
    BrowserModule,
    ComunModule,
    MentionModule
  ],
  declarations: [AdminComponent, PreguntasComponent,
     AsignacionesComponent, routingComponents, CuestionariosComponent,
     DialogAddP,
     DialogEditAsig,
     DialogAddC,
     ResultadosComponent,
     OpcionesComponent,
     CrearEncuestaComponent,
     ComentariosComponent,
     UnidadesComponent,
     TemasComponent,
     PuntosComponent,
     ConfirmacionComponent,
     EditarCuestionarioComponent,
     AdministracionComponent,
     PopUpAgregarUsuarioComponent,
     ConfiguracionComponent,
     FiltrosAsignacionesComponent,
     DerivadasComponent,
     PopUpAgregarTemaComponent,
     EvaluacionComponent,
     PopUpAddEvaluacionComponent
   ],
  schemas: [ NO_ERRORS_SCHEMA ],
  entryComponents: [DialogAddP, DialogEditAsig, DialogAddC, OpcionesComponent, ComentariosComponent, ConfirmacionComponent
  ,PopUpAgregarUsuarioComponent, PopUpAgregarTemaComponent, PopUpAddEvaluacionComponent]

})
export class AdministradorModule { }
