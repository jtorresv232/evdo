import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EvaluarCursosComponent } from './evaluar-cursos/evaluar-cursos.component';
import {EvaluarComponent} from './evaluar/evaluar.component';
import {FormularioComponent} from './formulario/formulario.component';

const routes: Routes = [
{path: '', component: EvaluarComponent,
    children: [{path: '', component: EvaluarCursosComponent}]},
{path: 'evaluar', component: EvaluarComponent,
    children: [{path: '', component: EvaluarCursosComponent},
              {path: 'formulario', component: FormularioComponent}]}
            ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EvaluarCursoEstudianteRoutingModule { }
export const routingComponents = [
  EvaluarComponent,
  EvaluarCursosComponent
];
