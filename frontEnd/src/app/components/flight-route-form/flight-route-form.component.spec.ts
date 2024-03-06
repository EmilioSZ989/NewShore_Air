import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightRouteFormComponent } from './flight-route-form.component';

describe('FlightRouteFormComponent', () => {
  let component: FlightRouteFormComponent;
  let fixture: ComponentFixture<FlightRouteFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FlightRouteFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FlightRouteFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
