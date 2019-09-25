import {Injectable} from '@angular/core';

@Injectable()
export class DocenteService {

  public isTeacherLoggedIn: boolean;
  public teacher: any;

  constructor() {
    this.isTeacherLoggedIn = false;
  }

  setStudentLoggedIn() {
    this.isTeacherLoggedIn = true;
  }

  getStudentLoggedIn() {
    return this.isTeacherLoggedIn;
  }
}
