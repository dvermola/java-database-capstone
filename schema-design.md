## MySQL Database Design

### Table: admin
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- username VARCHAR(50) NOT NULL UNIQUE,
- password VARCHAR(255) NOT NULL,  -- Stored as hashed value
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
- updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

### Table: patient 
- id BIGINT, PRIMARY KEY, AUTO_INCREMENT
- email VARCHAR(100), NOT NULL, UNIQUE  -- prevent from same email shared by several patients to increase quality of data
- password VARCHAR(512), NOT NULL       -- Stored as hashed value
- name VARCHAR(100), NOT NULL
- date_of_birth DATE, NOT NULL
- phone VARCHAR(10), NOT NULL
- address VARCHAR(255), NOT NULL
- is_active BOOLEAN DEFAULT TRUE,    -- deactivate patients without deleting them to keep history records
- created_at TIMESTAMP
- updated_at TIMESTAMP

### Table: doctor
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- email VARCHAR(100) NOT NULL UNIQUE,
- name VARCHAR(100), NOT NULL
- password VARCHAR(512) NOT NULL,  -- Stored as hashed value
- specialization VARCHAR(50) NOT NULL,
- phone VARCHAR(10) NOT NULL,
- profile_description TEXT,
- is_active BOOLEAN DEFAULT TRUE,    -- deactivate doctors without deleting them to keep history records
- created_at TIMESTAMP
- updated_at TIMESTAMP

### Table: appointment
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- doctor_id BIGINT NOT NULL,  FOREIGN KEY -> doctor(id), ON DELETE RESTRICT -- restrict deleting doctors if they have any appointments
- patient_id BIGINT NOT NULL, FOREIGN KEY -> patient(id), ON DELETE CASCADE -- if a patient is deleted, their appointments are also deleted
- start_time DATETIME NOT NULL,  -- has date and time of appointment start
- status INT (0 = 'SCHEDULED', 1 = 'COMPLETED', 2 = 'CANCELLED', 3 = 'NO_SHOW') DEFAULT 0,
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
- updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
CONSTRAINT unique_doctor_timeslot UNIQUE (doctor_id, appointment_date, start_time),
CHECK (end_time > start_time)

### Table: doctor_availability
- id BIGINT PRIMARY KEY AUTO_INCREMENT,
- doctor_id INT NOT NULL, FOREIGN KEY (doctor_id) -> doctor(_id) ON DELETE CASCADE,
- available_date DATE NOT NULL,
- available_time TIME NOT NULL, -- all appointments will be 1 hr long intervals
- CONSTRAINT unique_doctor_date UNIQUE (doctor_id, available_date, available_time)



## MongoDB Collection Design

### Collection: prescriptions
{
  "_id": ObjectId("60d21b4667d0d8992e610c85"),
  "patientName": "John Doe",
  "appointment_id": 789,
  "diagnosis": "Seasonal allergic rhinitis",
  "medication": "Loratadine",
  "dosage": "10mg",
  "doctorNotes": "Patient reports worsening symptoms during spring season. Take Loratadine in the morning with or without food",
  "created_at": ISODate("2023-06-15T10:30:00Z"),
  "updated_at": ISODate("2023-06-15T10:30:00Z")
}

