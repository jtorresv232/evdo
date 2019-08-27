import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { HttpService } from '../../comun/http.service';
import { DerivadasService } from '../../services/derivadas.service';


@Component({
  selector: 'app-pop-up-add-pregunta',
  templateUrl: './pop-up-add-pregunta.component.html',
  styleUrls: ['./pop-up-add-pregunta.component.css']
})
// tslint:disable-next-line:component-class-suffix
export class DialogAddP implements OnInit {
  public todasPreguntas = [];
  public cat: any;
  public texto: string;
  public categories: any = [];
  public preguntasForm: FormGroup;
  public derivadas;
  public numero_derivada;
  public loading = false;

  constructor(private _service: HttpService, public dialogRef: MatDialogRef<DialogAddP>
    , @Inject(MAT_DIALOG_DATA) public data: any, public snackBar: MatSnackBar,
    private _derivadaService: DerivadasService) { }

    
  ngOnInit() {
    this._service.getPreguntas().subscribe(data => {
      this.todasPreguntas = data;
    });
    this.categories = this.data;
    this.preguntasForm = new FormBuilder().group({
      pregunta: ['', Validators.required],
      tipo: ['', Validators.required],
      otro: [false, []],
      tema: ['', Validators.required],
      unica: [false, []],
      tipodato: ['', Validators.required],
      opciones: new FormBuilder().array([]),
      automatico: [false, []]
    });
  }

  agregarOpcion(): void {
    this.opciones.push(this.crearOpcionesFormGroup());
  }
  get opciones(): FormArray {
    return this.preguntasForm.get('opciones') as FormArray;
  }

  crearOpcionesFormGroup(): FormGroup {
    const form = new FormBuilder().group({
      texto: ['', Validators.required],
      mixta: [false, Validators.required]
    });
    return form;
  }

  openSnackBar(mensaje) {
    this.snackBar.open(mensaje, '', {
      duration: 1300,
    });
  }

  public closeDialog() {
    this.loading = true;
    if(!this.preguntasForm.get('automatico').value) {
      const datos = {
        // tslint:disable-next-line:radix
        tema: parseInt(this.preguntasForm.get('tema').value),
        pregunta: this.preguntasForm.get('pregunta').value,
        opcionUnica: !(this.preguntasForm.get('unica').value) ? 'N' : 'S',
        opcionOtro: !(this.preguntasForm.get('otro').value) ? 'N' : 'S',
        tipo: this.preguntasForm.get('tipo').value,
        tipodato: this.preguntasForm.get('tipodato').value
      };
      console.log(datos);

      this.derivadas = this._derivadaService.getLista();
      let resNumero;
      this._service.addPregunta(datos).subscribe(data => {
        resNumero = data['numero'];
        console.log(data);
        /* this._service.addPreguntaEv({
          numero: data['numero'],
          pregunta: data['pregunta'],
          tema: data['tema']
        }); */
        if(this.opciones.length > 0) {
          console.log(this.opciones.value);
          let opcionesData = this.opciones.value.map(x => x = {numero: data['numero'], texto: x.texto, mixta: x.mixta? 'SI' : 'NO'});
          console.log(opcionesData);
          this._service.addSeveralOptions(opcionesData).subscribe(respon => {
            console.log(respon);
             if(this.derivadas.length > 0) {
              let derivadasDatos = this.derivadas.map( x => x = {numero: resNumero,
                opcion: x.opcion,
                derivada: x.numero,
                secuencia: x.orden});
                this._service.addSeveralDerivadas(derivadasDatos).subscribe(resp => {console.log(resp);
                  this.dialogRef.close(data);
                  this.loading = false});
            } else {
              this.loading = false;
              this.dialogRef.close(data);
            }
          });
          /* for (let i = 0; i < this.opciones.length; i++) {
            const opdata = {
              numero: data['numero'],
              texto: this.opciones.get(i.toString()).get('texto').value,
              mixta: (this.opciones.get(i.toString()).get('mixta').value) ? 'SI' : 'NO'
            };

            let opcionesDatos = this.opciones.value;
            console.log(opcionesDatos);

            this._service.addOpciones(opdata).subscribe(res => {
              if (i == this.opciones.length -1) {
                console.log(res);
                this.dialogRef.close(data);
              }


              if(this.derivadas.length > 0) {
                let encontradas = this.derivadas.filter(x => x.opcion === res['opcion']);
                console.log(encontradas.length);
                if(encontradas.length > 0) {
                  for (let j = 0; j < encontradas.length; j++) {
                    const devData = {
                      numero: resNumero,
                      opcion: encontradas.opcion,
                      derivada: encontradas.numero,
                      secuencia: encontradas.orden
                    };
                    this._service.addDerivadas(devData).subscribe(resp => {
                      console.log(resp);
                    });
                  }
                }
              }


            }, error => {
              this.openSnackBar('No se ha podido agregar la pregunta correctamente');
            });
          } */

        } else {
          this.dialogRef.close(data);
        }
      }, err => {
        this.openSnackBar('No se ha podido guardar la pregunta');
      });
    } else {

      ////////////////
      const datos = {
        // tslint:disable-next-line:radix
        tema: parseInt(this.preguntasForm.get('tema').value),
        pregunta: this.preguntasForm.get('pregunta').value,
        opcionUnica: !(this.preguntasForm.get('unica').value) ? 'N' : 'S',
        opcionOtro: !(this.preguntasForm.get('otro').value) ? 'N' : 'S',
        tipo: this.preguntasForm.get('tipo').value,
        tipodato: this.preguntasForm.get('tipodato').value
      };
      console.log(datos);
  
      this._service.addPregunta(datos).subscribe(data => {
        console.log(data);
        let dat = {
          numero: data['numero'],
          pregunta: data['pregunta'],
          tema: data['tema']
        };
        console.log(dat);
        this._service.addPreguntaEv(dat).subscribe(x => { console.log(x)});
        for (let i = 1; i <= 5; i++) {
          console.log(i);
          const opdata = {
            numero: data['numero'],
            texto: i.toString(),
            mixta: 'NO'
          };
          this._service.addOpciones(opdata).subscribe(res => {
            this.dialogRef.close(data);
          });
        }
      }, err => {
      });
    }
  }

  public cancel() {
    console.log(this.preguntasForm.get('automatico'));
   this.dialogRef.close();
  }

  getType(tipo) {
    return (tipo == 'NUMERICO')? 'number' : 'text';
  }

  onSelection(tipo) {
    if (tipo != 'NUMERICO') {
      this.preguntasForm.get('automatico').setValue(false);
    }
  }

  getDerivadas(event) {
    console.log(event);
  }

}
