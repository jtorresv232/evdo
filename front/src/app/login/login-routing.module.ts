import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  {path:'', redirectTo:'/login', pathMatch:'full' },
  {path:'evaluar', redirectTo:'/evaluar', pathMatch:'full'},
  {path:'admin', redirectTo:'/admin', pathMatch:'full'},
  {path:'login', component:LoginComponent},
  {path:'docenteEvaluar', redirectTo:'/docenteEvaluar', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
export const routingComponents=[
  LoginComponent
]
