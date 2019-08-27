import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent} from './admin/admin.component';
import { PreguntasComponent} from './preguntas/preguntas.component';
import { CuestionariosComponent } from './cuestionarios/cuestionarios.component';
import {AsignacionesComponent } from './asignaciones/asignaciones.component';
import {ResultadosComponent} from './resultados/resultados.component';
import { CrearEncuestaComponent } from './crear-encuesta/crear-encuesta.component';
import { UnidadesComponent } from './unidades/unidades.component';
import { TemasComponent } from './temas/temas.component';
import { PuntosComponent } from './puntos/puntos.component';
import { EditarCuestionarioComponent } from './editar-cuestionario/editar-cuestionario.component';
import { AdministracionComponent } from './administracion/administracion.component';

const routes: Routes = [
{path: '', component: AdminComponent,
    children: [{path: '', component: PreguntasComponent}]},
{path: 'admin', component: AdminComponent,
    children: [{path: '', component: PreguntasComponent},
              {path: 'preguntas', component: PreguntasComponent},
              {path: 'asignaciones', component: AsignacionesComponent },
              {path: 'cuestionarios', component: CuestionariosComponent },
              {path: 'resultados', component: ResultadosComponent },
              {path: 'crear-encuesta', component: CrearEncuestaComponent},
              {path: 'unidades', component: UnidadesComponent},
              {path: 'temas', component: TemasComponent},
              {path: 'puntos', component: PuntosComponent},
              {path: 'editar-encuesta/:id', component: EditarCuestionarioComponent},
              {path: 'administracion', component: AdministracionComponent}
              ]}
            ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministradorRoutingModule { }
export const routingComponents = [AdminComponent, PreguntasComponent, CuestionariosComponent,
  AsignacionesComponent, CrearEncuestaComponent, UnidadesComponent];
