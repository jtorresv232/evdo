import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { HttpService } from '../../comun/http.service';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { MatDialog, MatDialogRef } from '@angular/material';
import { DataService } from '../../dataservice';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { PageEvent } from '@angular/material';
import { Sort } from '@angular/material';
import { Angular5Csv } from 'angular5-csv/Angular5-csv';
import * as XLSX from 'xlsx';
import { ComentariosComponent } from '../comentarios/comentarios.component';
import { Chart } from 'chart.js';

// tslint:disable-next-line:class-name
export interface totalPreg {
  numero: number;
  media: number;
  desviacion: number;
  coeficienteDesv: number;
  semestre: number;
  materia: number;
  grupo: number;
  cedula: string;
  cuenta: number;
}

// tslint:disable-next-line:class-name
export interface totalTem {
  codigoTema: number;
  media: number;
  desviacion: number;
  coeficienteDesv: number;
  semestre: number;
  materia: number;
  grupo: number;
  cedula: string;
  tema: string;
  cuenta: number;
}

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {
  public usuarioPortal: any;
  public arrayAsignaciones: any = [];
  public filt = '';
  public aDone = false;
  public filts = [];
  public categorias = ['Materia', 'Programa', 'Facultad', 'Tipo de docente', 'Cuestionario'];
  public nombre = '';
  public text;
  public length;
  public pageSizeOptions;
  public mostrar = false;
  public totalPreguntas: totalPreg[] = [];
  public totalTemas: totalTem[] = [];
  public totalpreguntasAsig: totalPreg[];
  public totaltemasAsig: totalTem[];
  public miprograma: any = 2;
  public total: any = 0;
  public grafico = false;
  // para los chips(botones emergentes) de los filtros
  separatorKeysCodes = [ENTER, COMMA];
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = false;
  /////////////////////////////////
  sortedDataPreguntas: totalPreg[];
  sortedDataTemas: totalTem[];
  // comentarios
  public dialogComentarios: MatDialogRef<ComentariosComponent>;
  @ViewChild('canvas') myDiv: ElementRef;
  chart: any = [];
  public chartDone = false;
  // pagination
  pageEvent: PageEvent;
  public programas: any = [];
  public docente: any;
  public facultad = 0;
  public programa: any;
  public materia: any;
  public filterProgramas: any = [];
  public filterProgramas2: any = [];
  public filterFacultades: any = [];
  public programanombre: any = '';
  public programanombre2: any = '';
  public facultadnombre = '';
  public preguntas: any = [];
  totalPreguntaPrograma: totalPreg[] = [];
  totalTemaPrograma: totalTem[] = [];
  totalPreguntaDocente: totalPreg[] = [];
  totalTemaDocente: totalTem[] = [];
  public docente2: any;
  public usuario: any;
  public tipo: any;
  public semestre: any;

  constructor(private _service: HttpService, public dialog: MatDialog, private data: DataService) {
  }

  dochart2(total, event) {
    if (event.index === 1) {
      console.log(total);
      const temas = [];
      const promedios = [];
      const coeficientes = [];
      const desviaciones = [];
      total.forEach(x => {
        promedios.push(x.media);
        coeficientes.push(x.coeficienteDesv);
        desviaciones.push(x.desviacion);
        temas.push(x.tema);
      });
      console.log(promedios);
      const totalPromedios = {
        label: 'Promedio',
        data: promedios,
        backgroundColor: '#6c9a06',
        borderWidth: 0
      };
      const totalCoeficientes = {
        label: 'Coeficiente Desv',
        data: coeficientes,
        backgroundColor: 'rgba(67, 92, 204)',
        borderWidth: 0
      };
      const totalDesviaciones = {
        label: 'Desviacion',
        data: coeficientes,
        backgroundColor: '#0c4022',
        borderWidth: 0
      };
      const chartOptions = {
        scales: {
          xAxes: [{
              ticks: {
                  autoSkip: false
              }
          }]
      }
      };

      Chart.defaults.global.defaultFontFamily = "Roboto";
      Chart.defaults.global.defaultFontSize = 12;

      const totalData = {
        labels: temas,
        datasets: [totalPromedios, totalDesviaciones, totalCoeficientes],
      };
      console.log(temas);
      this.chart = new Chart('radar', {
        type: 'bar',
        data: totalData,
        options: chartOptions
        /* type: 'radar',
        label: total.programa,
        data: {
          labels: temas,
          datasets: [
            {
              data: desviaciones,
              label: 'Desviacion',
              backgroundColor: 'rgba(67, 92, 204, 0.30)',
              borderColor: 'blue',
              pointBackgroundColor: 'darkblue'
            },
            {
              data: promedios,
              label: 'Media',
              backgroundColor: 'rgba(45, 178, 38, 0.30)',
              borderColor: 'green',
              pointBackgroundColor: 'darkgreen'
            },
            {
              data: coeficientes,
              label: 'Coef. Desviacion',
              backgroundColor: 'rgba(131, 131, 131, 0.30)',
              borderColor: 'gray',
              pointBackgroundColor: 'dargray'
            }
          ]
        },
        options: [] */

      });
    }
  }

  getTotalesPorDocente(cedula) {
    this._service.getTotalPreguntaDocente(cedula).map(response => response)
    .subscribe(res => {
      this.totalPreguntaDocente = res.reduce((final, actual) => {
        let sumando = res.filter(filtrando => filtrando.numero == actual.numero)
        .reduce((suma, actual2) => {
          if(final.find(x => x.numero == actual.numero) == undefined) {
            suma = {
              ...suma,
              media: suma['media'] + actual2['media'],
              cuenta: suma['cuenta'] ? suma['cuenta'] + 1 : 1,
              desviacion: suma['desviacion'] + actual2['desviacion'],
              coeficienteDesv: suma['coeficienteDesv'] + actual2['coeficienteDesv']
            };
            return suma;
          }
        });
        if(sumando !== undefined) {
          if (sumando['cuenta'] !== undefined) {
            sumando['cuenta'] += 1;
          } else {
            sumando['cuenta'] = 1;
          }
          const cuenta = sumando['cuenta'];
          sumando['coeficienteDesv'] /= cuenta;
          sumando['media'] /= cuenta;
          sumando['desviacion'] /= cuenta;
          sumando['coeficienteDesv'] = (sumando['coeficienteDesv']).toFixed(2);
          sumando['media'] = (sumando['media']).toFixed(2);
          sumando['desviacion'] = (sumando['desviacion']).toFixed(2);
          final.push(sumando);
        }
        return final;
      },[]);
    });

    this._service.getTotalTemaDocente(cedula).map(response => response)
    .subscribe(resp => {
      this.totalTemaDocente = resp.reduce((final, actual) => {
        let sumando = resp.filter(filtrando => filtrando.codigoTema == actual.codigoTema)
        .reduce((suma, actual2) => {
          if(final.find(x => x.codigoTema == actual.codigoTema) == undefined){
            suma = {
              ...suma,
              media: suma['media'] + actual2['media'],
              cuenta: suma['cuenta']? suma['cuenta'] + 1 : 1,
              desviacion: suma['desviacion'] + actual2['desviacion'],
              coeficienteDesv: suma['coeficienteDesv'] + actual2['coeficienteDesv']
            };
            return suma;
          }
        });
        if(sumando !== undefined) {
          if (sumando['cuenta'] !== undefined) {
            sumando['cuenta'] += 1;
          } else {
            sumando['cuenta'] = 1;
          }
          const cuenta = sumando['cuenta'];
          sumando['coeficienteDesv'] /= cuenta;
          sumando['media'] /= cuenta;
          sumando['desviacion'] /= cuenta;
          sumando['coeficienteDesv'] = (sumando['coeficienteDesv']).toFixed(2);
          sumando['media'] = (sumando['media']).toFixed(2);
          sumando['desviacion'] = (sumando['desviacion']).toFixed(2);
          final.push(sumando);
        }
        return final;
      }, []);
    });
  }

  doChart() {
    const programa = this.programas.find(x => x.nombrePrograma.toLowerCase() === this.programanombre2.toLowerCase()).programa;
    this._service.getTotalPreguntaPrograma(programa).map(response => response)
    .subscribe(resp => {
      //this.totalPreguntaPrograma = resp;
      this.totalPreguntaPrograma = resp.reduce((final, actual) => {
        let sumando = resp.filter(filtrando => filtrando.numero == actual.numero)
        .reduce((suma, actual2) => {
          if(final.find(x => x.numero == actual.numero) == undefined) {
            suma = {
              ...suma,
              media: suma['media'] + actual2['media'],
              cuenta: suma['cuenta'] ? suma['cuenta'] + 1 : 1,
              desviacion: suma['desviacion'] + actual2['desviacion'],
              coeficienteDesv: suma['coeficienteDesv'] + actual2['coeficienteDesv']
            };
            return suma;
          }
        });
        if(sumando !== undefined) {
          if (sumando['cuenta'] !== undefined) {
            sumando['cuenta'] += 1;
          } else {
            sumando['cuenta'] = 1;
          }
          const cuenta = sumando['cuenta'];
          sumando['coeficienteDesv'] /= cuenta;
          sumando['media'] /= cuenta;
          sumando['desviacion'] /= cuenta;
          sumando['coeficienteDesv'] = (sumando['coeficienteDesv']).toFixed(2);
          sumando['media'] = (sumando['media']).toFixed(2);
          sumando['desviacion'] = (sumando['desviacion']).toFixed(2);
          final.push(sumando);
        }
        return final;
      }, []);
      console.log(this.totalPreguntaPrograma);
    },
    err => {
      this.totalPreguntaPrograma = [];
    });
    this._service.getTotalTemaPrograma(programa).map(response => response)
    .subscribe(res => {
      //this.totalTemaPrograma = res;
      /////////////////////////
      let result = [];



      this.totalTemaPrograma = res.reduce((final, actual) => {
        let sumando = res.filter(filtrando => filtrando.codigoTema == actual.codigoTema)
        .reduce((suma, actual2) => {
          if(final.find(x => x.codigoTema == actual.codigoTema) == undefined){
            suma = {
              ...suma,
              media: suma['media'] + actual2['media'],
              cuenta: suma['cuenta']? suma['cuenta'] + 1 : 1,
              desviacion: suma['desviacion'] + actual2['desviacion'],
              coeficienteDesv: suma['coeficienteDesv'] + actual2['coeficienteDesv']
            };
            return suma;
          }
        });
        if(sumando !== undefined) {
          if (sumando['cuenta'] !== undefined) {
            sumando['cuenta'] += 1;
          } else {
            sumando['cuenta'] = 1;
          }
          const cuenta = sumando['cuenta'];
          sumando['coeficienteDesv'] /= cuenta;
          sumando['media'] /= cuenta;
          sumando['desviacion'] /= cuenta;
          sumando['coeficienteDesv'] = (sumando['coeficienteDesv']).toFixed(2);
          sumando['media'] = (sumando['media']).toFixed(2);
          sumando['desviacion'] = (sumando['desviacion']).toFixed(2);
          final.push(sumando);
        }
        return final;
      }, []);

      /////////////////////////
      console.log(result);
    },
    err => {
      this.totalTemaPrograma = [];
    });

    this._service.getProgramaNumeros(programa).map(response => response)
      .subscribe(nums => {
        console.log(nums);
        this.chart = new Chart('myPie', {
          type: 'pie',
          label: nums.nombrePrograma,
          data: {
            labels: ['Respondido', 'Sin Responder'],
            datasets: [
              {
                data: [nums.encuestados, nums.estudiantes - nums.encuestados],
                backgroundColor: [
                  'ForestGreen',
                  'DarkOrange'
                ],
                borderWidth: 1
              }
            ]
          },
          options: []
        });
      });
  }

  setPageSizeOptions(setPageSizeOptionsInput: string) {
    this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
  }

  ngOnInit() {
    this.pageEvent = { pageIndex: 0, pageSize: 5, length: this.arrayAsignaciones.length };
    console.log(this.pageEvent);
    this.data.changeMessage('admin');
    this.length = 0;

    this._service.getProgramas().map(response => response)
      .subscribe(r => {
        this.programas = r;
        this.filterProgramas = r;
      });

      this._service.getPreguntas().map(response => response)
    .subscribe(res => {
      console.log(res);
      this.preguntas = res;
    }, err => {
      console.log(err);
    });

    /* this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1, this.pageEvent.pageSize, this.facultad, {})
      .map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        this.aDone = true;
      });

    this._service.getAsigTotal(this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.total = res;
      }); */
    this._service.getTotalPreguntas().subscribe(resp => {
      this.totalPreguntas = resp;
      this.sortedDataPreguntas = this.totalPreguntas;
      console.log(this.totalPreguntas);
    });

    this._service.getTotalTemas().subscribe(respuesta => {
      this.totalTemas = respuesta;
      this.sortedDataTemas = this.totalTemas;
    });
  }

  // Exportar a CVS

  public exportarPreguntas() {
    const options = {
      fieldSeparator: ',',
      showLabels: true,
      showTitle: true,
      title: 'Total estadisticos por pregunta',
      headers: ['semestre', 'materia', 'grupo', 'cedula', 'pregunta',
        'media', 'desviacion', 'coeficienteDesv']
    };

    const mytotal = this.totalPreguntas.map(x => {
      const total = {
        semestre: x.semestre,
        materia: x.materia,
        grupo: x.grupo,
        cedula: x.cedula,
        pregunta: x.numero,
        media: x.media,
        desviacion: x.desviacion,
        coeficienteDesv: x.coeficienteDesv
      };
      return total;
    });
    // tslint:disable-next-line:no-unused-expression
    new Angular5Csv(mytotal, 'totalPreguntas' + new  Date().getTime(), options);
  }

  public exportarTemas() {
    const options = {
      fieldSeparator: ',',
      showLabels: true,
      showTitle: true,
      title: 'Total estadisticos por pregunta',
      headers: ['semestre', 'materia', 'grupo', 'cedula', 'tema', 'media', 'desviacion', 'coeficienteDesv']
    };

    const mytotal = this.totalTemas.map(x => {
      const total = {
        semestre: x.semestre,
        materia: x.materia,
        grupo: x.grupo,
        cedula: x.cedula,
        pregunta: x.codigoTema,
        media: x.media,
        desviacion: x.desviacion,
        coeficienteDesv: x.coeficienteDesv
      };
      return total;
    });

     // tslint:disable-next-line:no-unused-expression
     new Angular5Csv(mytotal, 'totalTemas' + new  Date().getTime(), options);
  }

  // Fin exportar a CVS

  toggleGraph() {
    this.grafico = !this.grafico;
  }

  public export() {
    const mytotal = this.totalPreguntas.map(x => {
      const total = {
        semestre: x.semestre,
        materia: x.materia,
        grupo: x.grupo,
        cedula: x.cedula,
        pregunta: x.numero,
        media: x.media,
        desviacion: x.desviacion,
        coeficienteDesv: x.coeficienteDesv
      };
      return total;
    });

    const workBook = XLSX.utils.book_new(); // create a new blank book
    const workSheet = XLSX.utils.json_to_sheet(mytotal);

    XLSX.utils.book_append_sheet(workBook, workSheet, 'data'); // add the worksheet to the book
    XLSX.writeFile(workBook, 'temp.xlsx'); // initiate a file download in browser
  }

  public getTotales(asig) {
    this.totalpreguntasAsig = this.totalPreguntas.filter(x =>
      x.semestre === asig.semestre &&
      x.materia === asig.materia &&
      x.grupo === asig.grupo &&
      x.cedula === asig.cedula
    );

    this.sortedDataPreguntas = this.totalpreguntasAsig;

    this.totaltemasAsig = this.totalTemas.filter(x =>
      x.semestre === asig.semestre &&
      x.materia === asig.materia &&
      x.grupo === asig.grupo &&
      x.cedula === asig.cedula
    );
    this.sortedDataTemas = this.totaltemasAsig;

  }

  public getTexto(asig) {
    return ('[' + asig.nombreMateria + '-' + asig.grupo.toString() + '] '
      + ' con: ' + asig.nombreDocente);
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;

  }

  add(): void {

    const obj = {
      nombre: this.nombre.toString(),
      text: this.text.toString(),
      indicador: this.nombre.toString()[0]
    };
    this.filts.push(obj);
  }

  remove(filt: any): void {
    const index = this.filts.indexOf(filt);

    if (index >= 0) {
      this.filts.splice(index, 1);
    }
  }// filter thing

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

  /* FILTROS */
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
  /* FIN FILTROS */

  setLength(len: any) {
    this.length = len;
  }

  public show() {
    this.mostrar = true;
    console.log(this.mostrar);
  }

  public notShow() {
    this.mostrar = false;
    console.log(this.mostrar);
  }

  public setmostrar() {
    if (this.mostrar) {
      return 'mostrar';
    } else {
      return 'nomostrar';
    }
  }

  change2(event) {
    this.programanombre2 = event;
    this.filterProgramas2 = this.filter(event);
  }

  filter(val) {
    return this.programas.filter(option =>
      option.nombrePrograma.toLowerCase().includes(val.toLowerCase()) === true);
  }

  // Comentarios
  public openDialogComentarios(asig) {
    this.dialogComentarios = this.dialog.open(ComentariosComponent, {panelClass: 'full-width-dialog', data: asig});
    this.dialogComentarios.afterClosed().subscribe(result => {
      console.log('Cerré comentarios');
      asig.comentarios = result.cuenta;
    });
  }

  // Fin Comentarios

  getnombre(numero) {
    let pregunta = this.preguntas.find(x => x.numero === numero);
    if(pregunta) {
      return pregunta.pregunta;
    } else {
      return 'Pregunta';
    }
  }

  // Exportar a excel

  sortDataPreguntas(sort: Sort) {
    const data = this.totalpreguntasAsig.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedDataPreguntas = data;
      return;
    }

    this.sortedDataPreguntas = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'numero': return compare(a.numero, b.numero, isAsc);
        case 'media': return compare(a.media, b.media, isAsc);
        case 'desviacion': return compare(a.desviacion, b.desviacion, isAsc);
        case 'coeficienteDesv': return compare(a.coeficienteDesv, b.coeficienteDesv, isAsc);
        default: return 0;
      }
    });
  }

  sortDataTemas(sort: Sort) {
    const data = this.totaltemasAsig.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedDataTemas = data;
      return;
    }

    this.sortedDataTemas = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'numero': return compare(a.codigoTema, b.codigoTema, isAsc);
        case 'media': return compare(a.media, b.media, isAsc);
        case 'desviacion': return compare(a.desviacion, b.desviacion, isAsc);
        case 'coeficienteDesv': return compare(a.coeficienteDesv, b.coeficienteDesv, isAsc);
        default: return 0;
      }
    });
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

function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

// Fin exportar excel
