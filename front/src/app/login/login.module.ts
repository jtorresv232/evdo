import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunModule } from '../comun/comun.module';
import {DataService} from '../dataservice';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login/login.component';

@NgModule({
  imports: [
    CommonModule,
    LoginRoutingModule,
    ComunModule
  ],
  declarations: [LoginComponent],
  providers:[DataService]
})
export class LoginModule { }
