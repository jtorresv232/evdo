import {Injectable} from '@angular/core';

@Injectable()
export class EstudianteService {

  public isStudentLoggedIn: boolean;
  public student: any;

  constructor() {
    this.isStudentLoggedIn = false;
  }

  setStudentLoggedIn() {
    this.isStudentLoggedIn = true;
  }

  getStudentLoggedIn(){
    return this.isStudentLoggedIn;
  }
}
