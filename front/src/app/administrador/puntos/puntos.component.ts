import { Component, OnInit } from '@angular/core';
import { HttpService } from '../../comun/http.service';

@Component({
  selector: 'app-puntos',
  templateUrl: './puntos.component.html',
  styleUrls: ['./puntos.component.css']
})
export class PuntosComponent implements OnInit {

  constructor(private _service: HttpService) { }
  public puntos: any = [];
  public rxp: any = [];
  public rxpFiltered: any = [];
  public pDone = false;

  ngOnInit() {
    this._service.getPuntos()
    .map(response => response)
    .subscribe(response => {
      this.puntos = response;
      this.pDone = true;
    });

    this._service.getRXP()
    .map(response => response)
    .subscribe(response => {
      this.rxp = response;
    });
  }

  filterPuntos(cPunto) {
    console.log(this.puntos);
    this.rxpFiltered = this.rxp.filter(rx => rx.punto === cPunto);
  }

}
