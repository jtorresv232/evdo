import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluarCursosComponent } from './evaluar-cursos.component';

describe('EvaluarCursosComponent', () => {
  let component: EvaluarCursosComponent;
  let fixture: ComponentFixture<EvaluarCursosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvaluarCursosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvaluarCursosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
