import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { HttpService } from '../../comun/http.service';

@Component({
  selector: 'app-filtros-asignaciones',
  templateUrl: './filtros-asignaciones.component.html',
  styleUrls: ['./filtros-asignaciones.component.css']
})
export class FiltrosAsignacionesComponent implements OnInit {

  /////////////// filtros //////////////////
  public facultades: any = [];
  public programas: any = [];
  public filterProgramas: any = [];
  public filterFacultades: any = [];
  public programanombre: any = '';
  public facultadnombre: any = '';
  public docente: any;
  public facultad = 0;
  public programa: any;
  public materia: any;
  public semestre: any;
  public tipo: any;

  @Output() infoFiltroEvento: EventEmitter<any> = new EventEmitter();

  constructor(private _service: HttpService) { }

  ngOnInit() {
    this._service.getProgramas().map(response => response)
      .subscribe(r => {
        this.programas = r;
      });

    this._service.getFacultades().map(response => response)
      .subscribe(re => {
        this.facultades = re;
      });
  }

  emitirInformacion (miEvento) {
    this.infoFiltroEvento.emit({...this.crearDatos(), evento: miEvento});
  }

  change(event) {
    this.programanombre = event;
    this.filterProgramas = this.filter(event);
  }

  changeFacultad(event) {
    this.facultadnombre = event;
    this.filterFacultades = this.filterFacultad(event);
  }

  filter(val) {
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

  filtrar() {console.log(this.programanombre);
    if (this.programanombre !== '') {
      this.programa = this.programas.find(x => x.nombrePrograma.toLowerCase() === this.programanombre.toLowerCase()).programa;
    }
    if (this.facultadnombre !== '') {
      this.facultad = this.facultades.find(x => x.nombre.toLowerCase() === this.facultadnombre.toLowerCase()).codigo;
    }
    this.emitirInformacion('filtrar');
  }

  limpiar() {
    this.facultad = 0;
    this.programa = null;
    this.materia = null;
    this.docente = null;
    this.programanombre = '';
    this.facultadnombre = '';
    this.semestre = 0;
    this.tipo = '';
    this.emitirInformacion('limpiar');
  }

  crearDatos() {
    return {
      facultad: this.facultad,
      programa: this.programa,
      materia: this.materia,
      docente: this.docente,
      programanombre: this.programanombre,
      facultadnombre: this.facultadnombre,
      semestre: this.semestre,
      tipo: this.tipo
    }
  }

}
