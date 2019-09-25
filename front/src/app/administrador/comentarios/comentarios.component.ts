import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { HttpService } from '../../comun/http.service';
import { FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-comentarios',
  templateUrl: './comentarios.component.html',
  styleUrls: ['./comentarios.component.css']
})
export class ComentariosComponent implements OnInit {

  public comentarios: any = [];
  public asignacion: any = [];
  public unidades: any = [{nombre: 'Facultad de Ingeniería', codigo: 1},
  {nombre: 'Facultad de ciencias exactas', codigo: 2}, {nombre: 'Facultad de medicina', codigo: 3}];
  myControl = new FormControl();
  public seleccionadas: any = [];
  public removable = true;
  @ViewChild('texto') inputEl: ElementRef;
  public texto = 'mi comentario';
  public user = 'Jonathan';
  public email = 'jtorresv232@gmail.com';
  public comentario =  '';
  public usuarioPortal: any;
  public facultad;
  public notify = false;

  constructor(private _service: HttpService, public dialogRef: MatDialogRef<ComentariosComponent>
    , @Inject(MAT_DIALOG_DATA) public data: any, public snackBar: MatSnackBar) { }

  ngOnInit() {

    this._service.getFiltroUser().map(response => response)
    .subscribe(resp => {
      console.log(resp);
      this.usuarioPortal = resp;
    });


    this.asignacion = this.data;
    const datos = {
      semestre : this.asignacion['semestre'],
      materia : this.asignacion['materia'],
      grupo : this.asignacion['grupo'],
      cedula : this.asignacion['cedula']

    };
    this._service.getComentariosEncuesta(datos).subscribe(res => {
      console.log(res);
      this.comentarios = res;
    });

    this._service.getFacultades().map(response => response)
    .subscribe(re => {
      this.unidades = re;
    });

    this._service.getProgramaById(this.asignacion['programa']).map(response => response)
    .subscribe(resp => {
      this.facultad = resp['facultad'];
      console.log(this.facultad);
    });
  }

  openSnackBar(mensaje) {
    this.snackBar.open(mensaje, '', {
      duration: 1300,
    });
  }

  send() {
    const current_date = new Date();
    let dataSend;
    const fecha = new DatePipe('en-US').transform(current_date, 'yyyy-MM-dd');
    // tslint:disable-next-line:max-line-length
    const comment = 'Lo invitamos a consultar en el módulo de evaluación docente la observación sobre el resultado de la evaluación del docente ' + this.asignacion['nombreDocente'] + ', curso: ' + this.asignacion['materia'] + ', programma ' + this.asignacion['programa'] + ', realizada por el Asistente de la Dirección de Posgrado.';
      const datos = {
        autor : this.usuarioPortal.displayName,
        emailAutor: this.usuarioPortal.email,
        fechaComentario: fecha,
        comentario: this.comentario,
        semestre : this.asignacion['semestre'],
        materia : this.asignacion['materia'],
        grupo : this.asignacion['grupo'],
        cedula : this.asignacion['cedula']
      };
      this._service.addComentario(datos).map(response => response)
      .subscribe(resp => {
        this.comentarios.push(resp);
        const data = {
          cuenta: this.comentarios.length
        };
        dataSend = data;
          this.dialogRef.close(data);
      });
    if(this.notify){
      const unidad = this.unidades.find(x => x.codigo = this.facultad);
      const mesage = {
        asunto : 'Observación sobre resultados de la evaluación Docentes, curso',
        cuerpo : comment,
        destinatario: unidad.email};
  
      this._service.notificarResultado(mesage).map(response => response)
      .subscribe(res => {
        console.log('se intenta notificar');
      });
    }
    this.openSnackBar('Se ha guardado tu comentario');
  }

  seleccionar(unidad) {
    this.seleccionadas.push(unidad);
    this.unidades.pop(unidad);
    this.inputEl.nativeElement.focus();
  }

  remove(seleccionada) {
    this.unidades.push(seleccionada);
    this.seleccionadas.pop(seleccionada);
  }

  public myselect(item) {
    console.log(item);
    // this.seleccionadas.push(item);
    return  item.nombre;
  }

  addhtml(item) {
    console.log('HEEEEYYYY');
  }


  public closeDialog() {
  }

  public cancel() {
    this.dialogRef.close();
  }

  public getLogo(autor) {
    let logo = '';
    const words = autor.trim().split(' ', 2);
    for (let i = 0; i < words.length; i++) {
      logo = logo + words[i].slice(0, 1);
    }
    return logo;
  }

}
