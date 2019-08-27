import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpAgregarUsuarioComponent } from './pop-up-agregar-usuario.component';

describe('PopUpAgregarUsuarioComponent', () => {
  let component: PopUpAgregarUsuarioComponent;
  let fixture: ComponentFixture<PopUpAgregarUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopUpAgregarUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpAgregarUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
