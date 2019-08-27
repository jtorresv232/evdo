import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DocentemainComponent} from './docentemain/docentemain.component';
import {EvaldocenteComponent} from './evaldocente/evaldocente.component';

const routes: Routes = [
{path: 'docente', component: DocentemainComponent,
    children: [{path: 'evaluaciones', component: EvaldocenteComponent}]}
            ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DocenteRoutingModule { }
export const routingComponents = [
  DocentemainComponent,
  EvaldocenteComponent
];
