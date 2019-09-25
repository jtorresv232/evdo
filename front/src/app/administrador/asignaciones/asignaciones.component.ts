import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';
import { Filtros } from '../../comun/mypipe';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { MatDialog, MatDialogRef } from '@angular/material';
import { DialogEditAsig } from '../pop-up-edit-asignacion/pop-up-edit-asignacion.component';
import { DatePipe } from '@angular/common';
import { DataService } from '../../dataservice';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { PageEvent } from '@angular/material';
import { ComentariosComponent } from '../comentarios/comentarios.component';

@Component({
  selector: 'app-asignaciones',
  templateUrl: './asignaciones.component.html',
  styleUrls: ['./asignaciones.component.css']
})
export class AsignacionesComponent implements OnInit {
  public arrayAsignaciones: any = [];
  public arrayCuestionarios: any = [];
  public filt = '';
  public dialogRef: MatDialogRef<DialogEditAsig>;
  public aDone = false;
  public qDone = false;
  public filts = [];
  public nombre = '';
  public text;
  public length;
  public pageSizeOptions;
  public dialogComentarios: MatDialogRef<ComentariosComponent>;
  public total: any = 0;
  public pDone = false;

  // Para los chips(botones emergentes) de los filtros
  separatorKeysCodes = [ENTER, COMMA];
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = false;
  /////////////// filtros //////////////////
  public programas: any = [];
  public programanombre: any = '';
  public facultadnombre: any = '';
  public docente: any;
  public facultad = 0;
  public programa: any;
  public materia: any;
  public tipo: any;
  public semestre: any;

  ///////////////
  public usuario: any;

  constructor(private _service: HttpService, public dialog: MatDialog, private data: DataService) { }

  pageEvent: PageEvent;

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }

  ngOnInit() {
    this.pageEvent = { pageIndex: 0, pageSize: 5, length: this.arrayAsignaciones.length };
    console.log(this.pageEvent);
    this.data.changeMessage('admin');
    this.length = 0;
    /* this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        console.log(this.arrayAsignaciones);
        this.aDone = true;
      }, err => {
        console.log(err);
      }); */

    /* this._service.getAsigTotal(this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.total = res;
      }); */

    this._service.getEncuestas().map(response => response)
      .subscribe(res => {
        this.arrayCuestionarios = res;
        this.qDone = true;
      }, err => {
        console.log(err);
      });

    this._service.getProgramas().map(response => response)
      .subscribe(r => {
        this.pDone = true;
        this.programas = r;
      });

      this._service.getFiltroUser()
    .map(res => res)
    .mergeMap(sesionUser => this._service.findUsuario(sesionUser.ccid))
    .subscribe(resp => {
      this.usuario = resp;
      console.log(resp);
    });
  }

  public limpiar() {
    this.pageEvent.pageIndex = 0;
    /* this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad, { programa: 0, materia: 0, cedula: '' }, this.semestre, this.tipo).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        console.log(this.arrayAsignaciones);
        this.aDone = true;
      }, err => {
        console.log(err);
      }); */

    /* this._service.getAsigTotal(this.facultad, { programa: 0, materia: 0, cedula: '' }, this.semestre? this.semestre : 0, 'ninguno').map(response => response)
      .subscribe(res => {
        this.total = res;
      }); */
      this.total = 0;
  }

  public getTexto(asig) {
    if (asig.encuesta) {
      return ('[ ' + asig.nombreMateria.toString() + '-' + asig.grupo.toString() + '-'
        + asig.nombreDocente.toString() + ' ] [' + (asig.fechaEncInicio).toString() + ' -- '
        + (asig.fechaEncFinal).toString() + ' E:' + asig.encuesta + ']');
    } else {
      return ('[ ' + asig.nombreMateria.toString() + '-' + asig.grupo.toString() + ' '
        + asig.nombreDocente.toString() + ' ]');
    }
  }

  public getStyle(asignacion) {
    if (!asignacion.encuesta) {
      return 'sinencuesta';
    }
  }

  actualizarPorcentaje(asig) {
    const metadato = asig.semestre.toString() + '-' + asig.materia.toString() + '-' +
      asig.grupo.toString() + '-' + asig.cedula;
    this._service.getCuentaEncuestados(metadato).subscribe(resp => {
      const datos = {
        semestre: asig.semestre,
        materia: asig.materia,
        grupo: asig.grupo,
        cedula: asig.cedula,
        estudiantes: asig.estudiantes,
        encuestados: (resp['cuenta']) ? resp['cuenta'] : 0
      };

      console.log(resp['cuenta']);
      if (resp['cuenta'] > 0) {
        this._service.editAsigPorcentaje(datos).subscribe(res => {
          const index = this.arrayAsignaciones.indexOf(asig);
          this.arrayAsignaciones[index]['porcentaje'] = res['porcentaje'];
          this.arrayAsignaciones[index]['encuestados'] = res['encuestados'];
        });
      }
    });
  }

  siguiente() {
    this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad, { programa: this.programa ? this.programa : 0,
         materia: this.materia ? this.materia : 0, cedula: '' }, this.semestre, this.tipo).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
      }, err => {
        console.log(err);
      });
  }

  public openDialogComentarios(asig) {
    this.dialogComentarios = this.dialog.open(ComentariosComponent, {
      data: asig
    });
    this.dialogComentarios.afterClosed().subscribe(result => {
      console.log('Cerré comentarios');
    });
  }

  public editarTodos() {
    this.dialogRef = this.dialog.open(DialogEditAsig, {
      data: this.arrayCuestionarios
    });
    this.dialogRef.afterClosed().subscribe(result => {
      if (result.estudiantes) {
        console.log(result.estudiantes);
        const fechaI = new DatePipe('en-US').transform(result.estudiantes.fecha_enc_inicio, 'yyyy-MM-dd');
        const fechaF = new DatePipe('en-US').transform(result.estudiantes.fecha_enc_final, 'yyyy-MM-dd');
        console.log(this.programanombre);
        let programa;
        if (this.programanombre !== '') {
          programa = this.programas.find(x => x.nombrePrograma === this.programanombre).programa;
        } else {
          programa = 0;
        }
        const datos = {
          programa: programa ? programa : 0,
          materia: this.materia ? this.materia : 0,
          cedula: this.docente ? this.docente : '',
          encuesta: result.estudiantes.encuesta.identificacion,
          fechaEncInicio: fechaI,
          fechaEncFinal: fechaF,
          semestre: this.semestre? this.semestre : 0,
          tipo_programa: this.tipo? this.tipo : 'ninguno'
        };
        console.log(datos);
        this._service.updAllAsigs(this.usuario.facultad, datos).subscribe(res => {
          console.log(res);
        });
        // asignar en el sistema de encuestas tambien.
      }
      if (result.profesor) {
        console.log(result.profesor);
        const fechaI = new DatePipe('en-US').transform(result.profesor.fecha_enc_inicio, 'yyyy-MM-dd');
        const fechaF = new DatePipe('en-US').transform(result.profesor.fecha_enc_final, 'yyyy-MM-dd');
        const programa = this.programas.find(x => x.nombrePrograma === this.programanombre).programa;
        const datos = {
          programa: programa ? programa : 0,
          materia: this.materia ? this.materia : 0,
          cedula: this.docente ? this.docente : '',
          encuestaprof: result.profesor.encuesta.identificacion,
          fechaEncprofInicio: fechaI,
          fechaEncprofFinal: fechaF,
          semestre: this.semestre? this.semestre : 0,
          tipo_programa: this.tipo? this.tipo : 'ninguno'
        };
        console.log(datos);
        this._service.updAllAsigsProf(this.usuario.facultad, datos).subscribe(res => {
          console.log(res);
        });
        // asignar en el sistema de encuestas tambien.
      }
      this.filtrar();
    });
  }

  public openDialog(asig) {
    this.dialogRef = this.dialog.open(DialogEditAsig, {
      data: this.arrayCuestionarios
    });
    this.dialogRef.afterClosed().subscribe((result) => {
      if (result.estudiantes) {
        console.log(result.estudiantes);
        const fechaI = new DatePipe('en-US').transform(result.estudiantes.fecha_enc_inicio, 'yyyy-MM-dd');
        const fechaF = new DatePipe('en-US').transform(result.estudiantes.fecha_enc_final, 'yyyy-MM-dd');
        const datos = {
          semestre: asig.semestre,
          materia: asig.materia,
          grupo: asig.grupo,
          cedula: asig.cedula,
          encuesta: result.profesor.encuesta.identificacion,
          fechaEncInicio: fechaI,
          fechaEncFinal: fechaF
        };
        console.log(datos);
        this._service.editAsignacion(datos).subscribe(res => {
          const index = this.arrayAsignaciones.indexOf(asig);
          console.log(res);
          this.arrayAsignaciones[index]['encuesta'] = res['encuesta'];
          this.arrayAsignaciones[index]['fechaEncFinal'] = res['fechaEncFinal'];
          this.arrayAsignaciones[index]['fechaEncInicio'] = res['fechaEncInicio'];
        });
        // asignar en el sistema de encuestas tambien.
        const metadato = asig.semestre.toString() + '-' + asig.materia.toString() +
          '-' + asig.grupo.toString() + '-' + asig.cedula;
        console.log(metadato);
      }
      /////////
      if (result.profesor) {
        console.log(result.profesor);
        const fechaI = new DatePipe('en-US').transform(result.profesor.fecha_enc_inicio, 'yyyy-MM-dd');
        const fechaF = new DatePipe('en-US').transform(result.profesor.fecha_enc_final, 'yyyy-MM-dd');
        const datos = {
          semestre: asig.semestre,
          materia: asig.materia,
          grupo: asig.grupo,
          cedula: asig.cedula,
          encuestaprof: result.profesor.encuesta.identificacion,
          fechaEncprofInicio: fechaI,
          fechaEncprofFinal: fechaF
        };
        console.log(datos);
        this._service.editAsignacionProf(datos).subscribe(res => {
          const index = this.arrayAsignaciones.indexOf(asig);
          console.log(res);
          this.arrayAsignaciones[index]['encuestaprof'] = res['encuestaprof'];
          this.arrayAsignaciones[index]['fechaEncprofFinal'] = res['fechaEncprofFinal'];
          this.arrayAsignaciones[index]['fechaEncprofInicio'] = res['fechaEncprofInicio'];
        });
        // asignar en el sistema de encuestas tambien.
        const metadato = asig.semestre.toString() + '-' + asig.materia.toString() +
          '-' + asig.grupo.toString() + '-' + asig.cedula;
        console.log(metadato);
      }
    });
  }

  add(): void {

    const obj = {
      nombre: 'Programa',
      text: this.programa.toString(),
      indicador: this.nombre.toString()[0]
    };
    this.filts.push(obj);
  }

  filtrar(): void {
    this.pageEvent.pageIndex = 0;
    this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad ? this.facultad : 0, { programa: this.programa ? this.programa : 0,
         materia: this.materia ? this.materia : 0,
         cedula: this.docente ? this.docente : ''}, this.semestre? this.semestre: 0, this.tipo? this.tipo : 'ninguno').map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        console.log(this.arrayAsignaciones);
        this.aDone = true;
      }, err => {
        console.log(err);
      });

      this._service.getAsigTotal(this.facultad, { programa: this.programa ? this.programa : 0,
         materia: this.materia ? this.materia : 0, cedula: this.docente ? this.docente : '' }, this.semestre? this.semestre: 0 , this.tipo? this.tipo : 'ninguno').map(response => response)
      .subscribe(res => {
        this.total = res;
        console.log(res);
      });
  }

  remove(filt: any): void {
    const index = this.filts.indexOf(filt);

    if (index >= 0) {
      this.filts.splice(index, 1);
    }
  }// filter thing


  setLength(len: any) {
    this.length = len;
  }

  getColor(asignacion) {
    if (asignacion.encuestaprof) {
      if (asignacion.respondida_prof === 'N') {
        return 'yellow';
      } else {
        return 'green';
      }
    } else {
      return 'black';
    }
  }

  perteneceFacultad(asignacion) {
    const programa = this.programas.find(x => x.programa === asignacion.programa);
    if (programa.facultad === this.usuario.facultad) {
      return true;
    } else {
      return false;
    }
  }

  leerInfoFiltros(event) {
    this.facultad = event['facultad'];
    this.facultadnombre = event['facultadnombre'];
    this.programa = event['programa'];
    this.programanombre = event['programanombre'];
    this.docente = event['docente'];
    this.materia = event['materia'];
    this.semestre = event['semestre'];
    this.tipo = event['tipo'];
    if(event['evento'] === 'limpiar') {
      this.limpiar();
    } else {
      this.filtrar();
    }
  }

}
