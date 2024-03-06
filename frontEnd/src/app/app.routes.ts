import { Routes } from '@angular/router';
import { PresentationComponent } from './components/presentation/presentation.component';
import { FlightRouteFormComponent } from './components/flight-route-form/flight-route-form.component';

export const routes: Routes = [
    { path: 'presentation', component: PresentationComponent },
    { path: 'flight-route', component: FlightRouteFormComponent },
    { path: '', redirectTo: '/presentation', pathMatch: 'full' }, // Redirecciona al componente de presentación por defecto
    { path: '**', redirectTo: '/presentation' } // Manejo de rutas no encontradas
  ];