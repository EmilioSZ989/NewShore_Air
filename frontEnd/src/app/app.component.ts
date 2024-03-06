import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FlightRouteFormComponent } from './components/flight-route-form/flight-route-form.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PresentationComponent } from './components/presentation/presentation.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FlightRouteFormComponent, NavbarComponent,PresentationComponent,FormsModule,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  selectedComponent = 'presentation';

  onSelectComponent(component: string): void {
    this.selectedComponent = component;
  }
}
