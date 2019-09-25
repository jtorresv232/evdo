import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpAgregarTemaComponent } from './pop-up-agregar-tema.component';

describe('PopUpAgregarTemaComponent', () => {
  let component: PopUpAgregarTemaComponent;
  let fixture: ComponentFixture<PopUpAgregarTemaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopUpAgregarTemaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpAgregarTemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
