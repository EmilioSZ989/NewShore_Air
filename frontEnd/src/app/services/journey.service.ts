import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { JourneyModel } from '../entities/journey-model';

@Injectable({
  providedIn: 'root'
})
export class JourneyService {
  private apiUrl = 'http://localhost:8080/api/journeys/route';

  constructor(private http: HttpClient) { }

  getFlightRoute(origin: string, destination: string): Observable<JourneyModel> {
    const url = `${this.apiUrl}?origin=${origin}&destination=${destination}`;
    return this.http.get<JourneyModel>(url).pipe(
      catchError(error => {
        // Manejar errores aquí
        throw 'Error al obtener la ruta de vuelo: ' + error;
      })
    );
  }
}