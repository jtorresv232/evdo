import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-mytoolbar',
  templateUrl: './mytoolbar.component.html',
  styleUrls: ['./mytoolbar.component.css']
})
export class MytoolbarComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit() {
  }

  public cerrarSesion(){
    this._router.navigate(['/login']);
  }

}
