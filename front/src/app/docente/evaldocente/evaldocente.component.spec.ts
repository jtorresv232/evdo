import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaldocenteComponent } from './evaldocente.component';

describe('EvaldocenteComponent', () => {
  let component: EvaldocenteComponent;
  let fixture: ComponentFixture<EvaldocenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvaldocenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvaldocenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
