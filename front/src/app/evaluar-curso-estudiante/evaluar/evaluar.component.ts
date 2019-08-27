import { Component, OnInit } from '@angular/core';
import { DataService } from '../../dataservice';

@Component({
  selector: 'app-evaluar',
  templateUrl: './evaluar.component.html',
  styleUrls: ['./evaluar.component.css']
})
export class EvaluarComponent implements OnInit {

  constructor(private data: DataService) { }

  ngOnInit() {
    this.data.changeMessage('student');

  }

}
