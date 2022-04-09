import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtmInterfaceComponent } from './atm-interface.component';

describe('AtmInterfaceComponent', () => {
  let component: AtmInterfaceComponent;
  let fixture: ComponentFixture<AtmInterfaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtmInterfaceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AtmInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
