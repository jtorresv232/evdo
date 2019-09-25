import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'myfilter',
  pure: false
})
export class MyFilterPipe implements PipeTransform {
  transform(grupos: any[], grupo: Object): any {

    if (grupo === undefined) {
      return grupos;
    }
    return grupos.filter(function (objeto) {
      return objeto.id === grupo;
    });
  }
}

@Pipe({
  name: 'pregFilter',
  pure: true
})
export class filterPregPipe implements PipeTransform {
  transform(preguntas: any[], value: any): any {
    if (preguntas) {
      if (value === undefined || value.pregunta === '') {
        return preguntas;
      }
      return preguntas.filter(x => x.pregunta.toLowerCase().includes(value.pregunta.toString().toLowerCase()));
    }
    return [];
    }
}

@Pipe({
  name: 'filtros',
  pure: false
})
export class Filtros implements PipeTransform {
  transform(asignaciones: any[], filts: any[], cuestionario: any): any {
    let array = asignaciones;
    let i;
    for (i = 0; i < filts.length; i++) {
      if (filts[i].nombre !== undefined) { // existe? categoria de filtro

        if (filts[i].nombre === 'Materia') {
          array = array.filter(function (objeto) {
            return objeto.materia__nombre.toLowerCase().includes(filts[i].text.toLowerCase());
          });
        } else if (filts[i].nombre === 'Programa') {
          console.log(array);
          array = array.filter(function (objeto) {
            return objeto.programa.toString().toLowerCase().includes(filts[i].text.toLowerCase());
          });
        } else if (filts[i].nombre === 'Facultad') {
          array = array.filter(function (objeto) {
            return objeto.materia__programa__facultad__nombre.toLowerCase().includes(filts[i].text.toLowerCase());
          });
        } else if (filts[i].nombre === 'Tipo de docente') {
          array = array.filter(function (objeto) {
            return objeto.docente__tipo.toLowerCase() === filts[i].text.toLowerCase() ||
              objeto.docente__tipoId.toLowerCase() === filts[i].text.toLowerCase();
          });
        } else if (filts[i].nombre === 'Cuestionario') {
          if (cuestionario) {
            const cuestionarios = cuestionario.filter(function (objeto) {
              return objeto.nombre.toString().toLowerCase().includes(filts[i].text.toLowerCase());
            });
            array = array.filter(function (objeto) {
              return cuestionarios.some(function (ej) {
                return ej.codigo === objeto.cuestionario;
              });
            });
          }
        }
      }//
    }
    return array;
  }
}

@Pipe({
  name: 'filters',
  pure: false
})
export class Filters implements PipeTransform {
  transform(asignaciones: any[], filts: any[], materias: any, programas: any, facultad: any,
    docente: any, cuestionario: any): any {
    let array = asignaciones;
    let i;
    // console.log(filts[0]);
    for (i = 0; i < filts.length; i++) {
      if (filts[i].nombre !== undefined) {
        if (filts[i].nombre === 'Materia') {
          const materia = materias.filter(function (objeto) {
            return objeto.nombre.toString().toLowerCase().includes(filts[i].text.toLowerCase());
          });

          array = array.filter(function (objeto) {
            return materia.some(function (ej) {
              return ej.codigo === objeto.materia;
            });
          });
        } else if (filts[i].nombre === 'Programa') {
          if (programas) {
            // programas por nombre
            const programa = programas.filter(function (objeto) {
              return objeto.nombre.toLowerCase().includes(filts[i].text.toLowerCase());
            });

            // tslint:disable-next-line:no-shadowed-variable
            const materias_programa = materias.filter(function (objeto) {
              return programa.some(function (ej) {
                return ej.codigo === objeto.programa;
              });
            });
            array = array.filter(function (objeto) {
              return materias_programa.some(function (ej) {
                return ej.codigo === objeto.materia;
              });
            });
          }
        } else if (filts[i].nombre === 'Facultad') {
          if (programas && materias && facultad) {
            // obtenemos la facultad
            const facultades = facultad.filter(function (obj) {
              return obj.nombre.toLowerCase().includes(filts[i].text.toLowerCase());
            });
            // programas por facultad
            const programas_facultad = programas.filter(function (objeto) {
              return facultades.some(function (oj) {
                return oj.codigo === objeto.facultad;
              });
            });
            // materias por programa de facultad
            const materias_programa = materias.filter(function (objeto) {
              return programas_facultad.some(function (ej) {
                return ej.codigo === objeto.programa;
              });
            });
            // asignaciones de esas materias por programa por facultad
            array = array.filter(function (objeto) {
              return materias_programa.some(function (ej) {
                return ej.codigo === objeto.materia;
              });
            });
          }
        } else if (filts[i].nombre === 'Tipo de docente') {
          if (docente) {
            const docentes = docente.filter(function (objeto) {
              return objeto.tipoId === filts[i].text || objeto.tipo === filts[i].text;
            });
            array = array.filter(function (objeto) {
              return docentes.some(function (ej) {
                return ej.cedula === objeto.docente;
              });
            });
          }
        } else if (filts[i].nombre === 'Cuestionario') {
          if (cuestionario) {
            const cuestionarios = cuestionario.filter(function (objeto) {
              return objeto.nombre.toString().toLowerCase().includes(filts[i].text.toLowerCase());
            });
            array = array.filter(function (objeto) {
              return cuestionarios.some(function (ej) {
                return ej.codigo === objeto.cuestionario;
              });
            });
          }
        }
      }
    }
    return array;
  }
}

@Pipe({
  name: 'myfilter2',
  pure: false
})
export class MyFilterPipe2 implements PipeTransform {
  transform(grupos: any[], grupo: Object): any {

    if (grupo === undefined) {
      return grupos;
    }
    return grupos.filter(function (objeto) {
      return objeto.grupo === grupo;
    });
  }
}


@Pipe({
  name: 'filterByName',
  pure: false
})
export class MyFilterName implements PipeTransform {
  transform(objeto: any[], nombre: Object): any {

    if (nombre === undefined) {
      return objeto;
    }
    const nombretxt = nombre.toString();
    return objeto.filter(function (obj) {
      return obj.nombre.toLowerCase().includes(nombretxt.toLowerCase());
    });
  }
}

@Pipe({
  name: 'filterByCategoria',
  pure: false
})
export class MyFilterCategory implements PipeTransform {
  transform(preguntas: any[], categoria: Object): any {
    if (categoria === undefined) {
      return preguntas;
    }
    if (categoria === '') {
      return preguntas;
    }
    const nombretxt = categoria;
    return preguntas.filter(function (obj) {
      return obj.tema === (nombretxt);
    });
  }
}

@Pipe({
  name: 'filterPregs',
  pure: false
})
export class filterPregs implements PipeTransform {
  transform(preguntas: any[], selecteds: any[]): any {
    if (selecteds === undefined) {
      return preguntas;
    }
    return preguntas.filter(function (obj) {
      return selecteds.indexOf(obj.numero) < 0;
    });
  }
}
