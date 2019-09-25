import { Injectable } from '@angular/core';

@Injectable()
export class DerivadasService {

  listaderivadas= [];

  constructor() { }

  getLista() {
    return this.listaderivadas;
  }

  add(pregunta) {
    let preg = this.listaderivadas.find(x => x.opcion === pregunta.opcion && x.orden === pregunta.orden);
    if(preg)
    {
      let index = this.listaderivadas.indexOf(preg);
      this.listaderivadas[index] = pregunta;
    } else {
      this.listaderivadas.push(pregunta);
    }
  }

  delete(pregunta) {
    let pre = this.listaderivadas.find(x => x.numero = pregunta.numero);
    let index = this.listaderivadas.indexOf(pre);
    this.listaderivadas.splice(index, 1);
  }

}
