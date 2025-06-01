## MySQL Database Design

// TODO: Write your design clearly and justify choices as comments in the file if needed.

### Table: patient 
- id BIGINT, PRIMARY KEY, AUTO_INCREMENT
- email VARCHAR(100), NOT NULL, UNIQUE  -- prevent from same email shared by several patients to increase quality of data
- password VARCHAR(512), NOT NULL       -- Stored as hashed value
- first_name VARCHAR(50), NOT NULL
- last_name VARCHAR(50), NOT NULL
- date_of_birth DATE, NOT NULL
- phone VARCHAR(20), NOT NULL
- address VARCHAR(255), NOT NULL
- is_active BOOLEAN DEFAULT TRUE,    -- deactivate patients without deleting them to keep history records
- created_at TIMESTAMP
- updated_at TIMESTAMP

### Table: doctor
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- email VARCHAR(100) NOT NULL UNIQUE,
- password VARCHAR(512) NOT NULL,  -- Stored as hashed value
- first_name VARCHAR(50) NOT NULL,
- last_name VARCHAR(50) NOT NULL,
- specialization VARCHAR(100) NOT NULL,
- phone VARCHAR(20) NOT NULL,
- profile_description TEXT,
- is_active BOOLEAN DEFAULT TRUE,    -- deactivate doctors without deleting them to keep history records
- created_at TIMESTAMP
- updated_at TIMESTAMP

### Table: admin
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- email VARCHAR(100) NOT NULL UNIQUE,
- password VARCHAR(255) NOT NULL,  -- Stored as hashed value
- first_name VARCHAR(50) NOT NULL,
- last_name VARCHAR(50) NOT NULL,
- role VARCHAR(50) DEFAULT 'ADMIN',
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
- updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

### Table: appointment
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- patient_id BIGINT NOT NULL,
- doctor_id BIGINT NOT NULL,
- date DATE NOT NULL,
- start_time TIME NOT NULL,
- end_time TIME NOT NULL,
- status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED', 'NO_SHOW') DEFAULT 'SCHEDULED',
- notes TEXT,
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
- updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,   -- if a patient is deleted, their appointments are also deleted
FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE RESTRICT,    -- restrict deleting doctors if they have any appointments
CONSTRAINT unique_doctor_timeslot UNIQUE (doctor_id, appointment_date, start_time),
CHECK (end_time > start_time)

### Table: doctor_availability
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- doctor_id INT NOT NULL,
- unavailable_date DATE NOT NULL,
- unavailable_start_time TIME,
- unavailable_end_time TIME,
- is_all_day BOOLEAN DEFAULT FALSE,
- reason VARCHAR(255),
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
- FOREIGN KEY (doctor_id) REFERENCES doctor(_id) ON DELETE CASCADE,
- CONSTRAINT unique_doctor_date UNIQUE (doctor_id, unavailable_date, unavailable_start_time)

### Table: log
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- context ENUM('PAT', 'ADM', 'DOC', 'APP', 'PRESCR') NOT NULL,  -- type of the entity involved: Patient, Admin, Doctor, Appointment, Prescription 
- context_id BIGINT NOT NULL,  -- id of the entity involved 
- event ENUM('ADD', 'UPD', 'DEL', 'CNL') NOT NULL,  -- what event happend: add, delete, update, cancel, 
- details VARCHAR(4000) NOT NULL,
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

### Table payment
- id INT PRIMARY KEY AUTO_INCREMENT,
- appointment_id INT NOT NULL,
- amount DECIMAL(10, 2) NOT NULL,
- payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
- payment_method ENUM('CREDIT_CARD', 'DEBIT_CARD', 'CASH', 'INSURANCE') NOT NULL,
- status ENUM('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED') DEFAULT 'PENDING',
- transaction_reference VARCHAR(100),
- FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE RESTRICT


## MongoDB Collection Design

### Collection: prescriptions
{
  "_id": ObjectId("60d21b4667d0d8992e610c85"),
  "patient_id": 123,
  "doctor_id": 456,
  "appointment_id": 789,
  "created_at": ISODate("2023-06-15T10:30:00Z"),
  "updated_at": ISODate("2023-06-15T10:30:00Z"),
  "diagnosis": "Seasonal allergic rhinitis",
  "notes": "Patient reports worsening symptoms during spring season",
  "medications": [
    {
      "name": "Loratadine",
      "dosage": "10mg",
      "frequency": "Once daily",
      "duration": "30 days",
      "instructions": "Take in the morning with or without food"
    },
    {
      "name": "Fluticasone nasal spray",
      "dosage": "50mcg per nostril",
      "frequency": "Twice daily",
      "duration": "30 days",
      "instructions": "Use in both nostrils morning and evening"
    }
  ],
  "follow_up": {
    "required": true,
    "timeframe": "4 weeks",
    "notes": "Return if symptoms persist or worsen"
  },
  "attachments": [
    {
      "filename": "allergy_test_results.pdf",
      "content_type": "application/pdf",
      "upload_date": ISODate("2023-06-15T10:35:00Z"),
      "file_id": ObjectId("60d21b4667d0d8992e610c86")
    }
  ]
}


### Collection: medical_logs 
{ 
  "_id": ObjectId("61a2c4f8b7e31d45a9c8d7e6"),
  "patient_id": 145,  
  "doctor_id": 37,
  "log_type": "SYMPTOM_TRACKER",
  "created_at": ISODate("2023-08-05T08:30:00Z"),
  "entries": [
    {
      "date": ISODate("2023-08-01T07:15:00Z"),
      "symptoms": ["headache", "fatigue"],
      "severity": 3,
      "duration": "4 hours",
      "triggers": ["stress", "lack of sleep"],
      "notes": "Occurred after working late"
    },
    {
      "date": ISODate("2023-08-03T14:20:00Z"),
      "symptoms": ["headache"],
      "severity": 2,
      "duration": "2 hours",
      "triggers": ["bright lights"],
      "notes": "Improved after taking prescribed medication"
    }
  ],
  "medication_adherence": [
    {
      "medication_name": "Loratadine",
      "dates_taken": [
        ISODate("2023-08-01T08:00:00Z"),
        ISODate("2023-08-02T08:15:00Z"),
        ISODate("2023-08-03T08:00:00Z")
      ],
      "missed_doses": 1,
      "side_effects": ["drowsiness"]
    }
  ],
  "vitals": [
    {
      "date": ISODate("2023-08-01T08:00:00Z"),
      "blood_pressure": "120/80",
      "heart_rate": 72,
      "temperature": 98.6,
      "weight": 165.5,
      "glucose": 95
    },
    {
      "date": ISODate("2023-08-03T08:00:00Z"),
      "blood_pressure": "118/78",
      "heart_rate": 70,
      "temperature": 98.4,
      "weight": 165.0,
      "glucose": 92
    }
  ],
  "shared_with_doctors": [37, 42],
  "last_reviewed": {
    "doctor_id": 37,
    "timestamp": ISODate("2023-08-06T10:15:00Z"),
    "notes": "Patient showing improvement with current treatment plan"
  }
}

### Collection: patient_feedback
{
  "_id": ObjectId("62b3d5f9c8e47a2b1d9f3e2a"),
  "patient_id": 123,
  "appointment_id": 789,
  "doctor_id": 456,
  "submitted_at": ISODate("2023-07-15T14:30:00Z"),
  "overall_rating": 4,
  "categories": {
    "doctor_communication": 5,
    "staff_friendliness": 4,
    "wait_time": 3,
    "facility_cleanliness": 5,
    "treatment_effectiveness": 4
  },
  "comments": "Dr. Smith was very thorough and explained my condition clearly. Wait time was a bit longer than expected.",
  "follow_up_requested": false,
  "anonymous": false,
  "tags": ["annual checkup", "positive experience"],
  "status": "REVIEWED",
  "admin_notes": {
    "reviewed_by": "admin_id_42",
    "reviewed_at": ISODate("2023-07-16T09:15:00Z"),
    "internal_comments": "Patient highlighted wait time issue, scheduling team notified"
  }
}
