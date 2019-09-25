import { NgModule} from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import { ConexionMisNotasComponent } from './conexion-mis-notas/conexion-mis-notas.component';

const routes: Routes = [
{path: '', redirectTo: '/login', pathMatch: 'full'},
{path: 'evaluar', redirectTo: '/evaluar', pathMatch: 'full'},
{path: 'admin', redirectTo: '/admin', pathMatch: 'full'},
{path: 'docente', redirectTo: '/docente', pathMatch: 'full'},
{path: 'evdo/conexion-portal/:semestre/:materia/:grupo', component: ConexionMisNotasComponent}
];

@NgModule({
imports: [RouterModule.forRoot(routes, {useHash: true})],
exports: [RouterModule]
})
export class StatsRoutingModule {}
export const routingComponents = [
];


