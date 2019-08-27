import { Component, OnInit } from '@angular/core';
import {DataService} from '../../dataservice';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  public message: string;

  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.changeMessage('admin');
  }

  public getMessage($event) {
    this.message = $event;
  }



}
