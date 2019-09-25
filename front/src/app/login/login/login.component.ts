import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { DataService } from "../../dataservice";
import {EstudianteService} from "../../services/estudiante.service";
import {DocenteService} from "../../services/docente.service";
import {AdminService} from "../../services/admin.service";
import {HttpService} from '../../comun/http.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public roles = [
    {value: '0', viewValue: 'Estudiante'},
    {value: '1', viewValue: 'Profesor'},
    {value: '2', viewValue: 'Administrador'}
  ];

  public opcion:any;
  public password:string;
  public email:string;

  constructor(private _router: Router, private data: DataService, private studentService:EstudianteService, private _service: HttpService, private teacherService:DocenteService, private adminService: AdminService) { }

  ngOnInit() {
  }

  reDireccionar(option){
    if(option=='2'){
      console.log('por admin');
      this.data.changeMessage('admin');
      this._service.getCoordinador(this.email,this.password).map(response=>response)
      .subscribe(res=>{
        this.adminService.admin=res;
        if(this.adminService.admin.nombre!=""){
          console.log('weeeeeey');
        this.adminService.setStudentLoggedIn();
        console.log(this.adminService.admin);
        this._router.navigate(['/admin']);
      }
      },err=>{
        console.log(err);
      });

  }else if(option=='0'){
    console.log('por estudiante');
    this.data.changeMessage('student');


      this._service.getEstudiante(this.email,this.password).map(response=>response)
      .subscribe(res=>{
        this.studentService.student=res;
        if(this.studentService.student.nombre!=""){
          console.log('weeeeeey');
        this.studentService.setStudentLoggedIn();
        console.log(this.studentService.student);
        this._router.navigate(['/evaluar']);
      }
      },err=>{
        console.log(err);
      });

  }else{
    this.data.changeMessage('teacher');


      this._service.getDocente(this.email,this.password).map(response=>response)
      .subscribe(res=>{
        this.teacherService.teacher=res;
        if(this.teacherService.teacher.nombre!=""){
          console.log('weeeeeey');
        this.teacherService.setStudentLoggedIn();
        console.log(this.teacherService.teacher);
        this._router.navigate(['/docenteEvaluar']);
      }
      },err=>{
        console.log(err);
      });
  }
  }

}
