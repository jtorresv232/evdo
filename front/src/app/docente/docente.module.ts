import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import {ComunModule} from '../comun/comun.module';
import { DocenteRoutingModule,routingComponents} from './docente-routing.module';
import { DocentemainComponent } from './docentemain/docentemain.component';
import { EvaldocenteComponent } from './evaldocente/evaldocente.component';

@NgModule({
  imports: [
    CommonModule,
    DocenteRoutingModule,
    BrowserModule,
    ComunModule
  ],
  declarations: [routingComponents, DocentemainComponent, EvaldocenteComponent],
  schemas: [ NO_ERRORS_SCHEMA ],
})
export class DocenteModule { }
