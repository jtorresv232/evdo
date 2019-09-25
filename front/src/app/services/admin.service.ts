import {Injectable} from '@angular/core';

@Injectable()
export class AdminService {

  public isAdminLoggedIn: boolean;
  public admin: any;

  constructor() {
    this.isAdminLoggedIn = false;
  }

  setStudentLoggedIn() {
    this.isAdminLoggedIn = true;
  }

  getStudentLoggedIn() {
    return this.isAdminLoggedIn;
  }
}
