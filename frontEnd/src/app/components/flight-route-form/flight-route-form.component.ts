import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { JourneyService } from '../../services/journey.service';
import { CommonModule } from '@angular/common';
import { JourneyModel } from '../../entities/journey-model';

@Component({
  selector: 'app-flight-route-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './flight-route-form.component.html',
  styleUrl: './flight-route-form.component.css'
})

export class FlightRouteFormComponent implements OnInit {
  flightRouteForm!: FormGroup;
  journey!: JourneyModel;
  conversionRates: { [key: string]: number } = { USD: 1, COP: 4000, MXN: 20 };

  constructor(private formBuilder: FormBuilder, private journeyService: JourneyService) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.flightRouteForm = this.formBuilder.group({
      origin: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]],
      destination: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(3)]],
      currency: ['USD'],
    });
  }

  onSubmit(): void {
    if (!this.flightRouteForm) return;
    
    const origin = this.flightRouteForm.value.origin.toUpperCase();
    const destination = this.flightRouteForm.value.destination.toUpperCase();
    this.journeyService.getFlightRoute(origin, destination).subscribe(
      (journey: JourneyModel) => {
        this.journey = journey;
        this.updatePrices();
      },
      error => {
        console.error('Error al obtener la ruta de vuelo:', error);
        alert('No se pudo encontrar una ruta de vuelo. Por favor, intenta con otros destinos.');
      }
    );
  }

  updatePrices(): void {
    if (!this.journey || !this.flightRouteForm) return;

    const currency = this.flightRouteForm.value.currency;
    const conversionRate = this.conversionRates[currency as keyof typeof this.conversionRates] || 1;

    this.journey.price *= conversionRate;
    for (const flight of this.journey.flights) {
      flight.price *= conversionRate;
    }
  } 
}
