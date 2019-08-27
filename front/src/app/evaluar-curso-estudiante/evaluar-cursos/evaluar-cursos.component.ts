import { Component, OnInit } from '@angular/core';
import {HttpService} from '../../comun/http.service';
import {MatSnackBar, PageEvent} from '@angular/material';
import {Dialog} from '../popup-encuesta/popup-encuesta.component';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import {MatDialog, MatDialogRef} from '@angular/material';
import { Router, NavigationExtras} from '@angular/router';
import {EstudianteService} from '../../services/estudiante.service';



@Component({
  selector: 'app-evaluar-cursos',
  templateUrl: './evaluar-cursos.component.html',
  styleUrls: ['./evaluar-cursos.component.css']
})
export class EvaluarCursosComponent implements OnInit {

  public arrayAsignaciones: any = [];
  pageEvent: PageEvent;
  public length;
  public total: any = 0;
  public aDone = false;
  public facultad = 0;
  public programa: any;
  public materia: any;

  ////////
  public filterProgramas: any = [];
  public filterFacultades: any = [];
  public programanombre: any = '';
  public facultadnombre: any = '';
  public docente: any;
  public facultades: any = [];
  public programas: any = [];
  public pDone = false;

  constructor(private _service: HttpService, public snackBar: MatSnackBar, public dialog: MatDialog,
    private _router: Router) { }

  ngOnInit() {
    this.pageEvent = { pageIndex: 0, pageSize: 5, length: this.arrayAsignaciones.length };
    console.log(this.pageEvent);
    this.length = 0;
    this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        console.log(this.arrayAsignaciones);
        this.aDone = true;
      }, err => {
        console.log(err);
      });

    this._service.getAsigTotal(this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.total = res;
      });

      this._service.getProgramas().map(response => response)
      .subscribe(r => {
        this.pDone = true;
        this.programas = r;
      });

    this._service.getFacultades().map(response => response)
      .subscribe(re => {
        this.facultades = re;
      });
  }

  siguiente() {
    this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad, { programa: this.programa ? this.programa : 0,
         materia: this.materia ? this.materia : 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
      }, err => {
        console.log(err);
      });
  }

  redireccionar(asignacion) {
    this._router.navigate(['/evaluar/formulario', asignacion]);
    console.log(asignacion);
  }

  //////////////////////////////
  change(event) {
    this.programanombre = event;
    console.log(this.programanombre);
    this.filterProgramas = this.filter(event);
  }

  changeFacultad(event) {
    this.facultadnombre = event;
    console.log(this.facultadnombre);
    this.filterFacultades = this.filterFacultad(event);
  }

  filter(val) {
    console.log(val);
    return this.programas.filter(option =>
      option.nombrePrograma.toLowerCase().includes(val.toLowerCase()) === true);
  }

  filterFacultad(val) {
    return this.facultades.filter(option =>
      option.nombre.toLowerCase().includes(val.toLowerCase()) === true);
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  filtrar(): void {
    if (this.programanombre !== '') {
      this.programa = this.programas.find(x => x.nombrePrograma.toLowerCase() === this.programanombre.toLowerCase()).programa;
    }
    console.log(this.facultadnombre);
    if (this.facultadnombre !== '') {
      this.facultad = this.facultades.find(x => x.nombre.toLowerCase() === this.facultadnombre.toLowerCase()).codigo;
      console.log(this.facultad);
    }
    this.pageEvent.pageIndex = 0;
    this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad ? this.facultad : 0, { programa: this.programa ? this.programa : 0,
         materia: this.materia ? this.materia : 0,
         cedula: this.docente ? this.docente : '' }).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        console.log(this.arrayAsignaciones);
        this.aDone = true;
      }, err => {
        console.log(err);
      });

      this._service.getAsigTotal(this.facultad, { programa: this.programa ? this.programa : 0,
         materia: this.materia ? this.materia : 0, cedula: this.docente ? this.docente : '' }).map(response => response)
      .subscribe(res => {
        this.total = res;
        console.log(res);
      });
  }

  public limpiar() {
    this.facultad = 0;
    this.programa = null;
    this.materia = null;
    this.docente = null;
    this.programanombre = '';
    this.pageEvent.pageIndex = 0;
    this._service.getAsignacionesFiltered(this.pageEvent.pageIndex + 1,
      this.pageEvent.pageSize, this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.arrayAsignaciones = res;
        console.log(this.arrayAsignaciones);
        this.aDone = true;
      }, err => {
        console.log(err);
      });

    this._service.getAsigTotal(this.facultad, { programa: 0, materia: 0, cedula: '' }).map(response => response)
      .subscribe(res => {
        this.total = res;
      });
  }
}
