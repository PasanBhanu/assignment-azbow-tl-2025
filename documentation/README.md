# System Design Decisions

The system was designed with the necessary apis to handle the state transition of lead entity. 

There are 2 roles in the system which created to address role-based access functional requirement. APIs are restricted for a specific role or public. For authentication, Basic Authentication is used instead of JWT. 

Role based restrictions are forced through api filtering. Method security is not used considering the simplicity of the task.

# State Diagram

Below diagram shows how the lead status is transitionaing between status based on the actions.

```mermaid
stateDiagram-v2
    [*] --> UNASSIGNED
    UNASSIGNED --> ASSIGNED
    ASSIGNED --> RESERVATION
    RESERVATION --> ASSIGNED : cancel
    RESERVATION --> FINANCIAL_APPROVED : financial approved
    RESERVATION --> [*] : financial rejected
    FINANCIAL_APPROVED --> LEGAL_FINALIZED : contract singed
    FINANCIAL_APPROVED --> [*] : contract rejected 
    LEGAL_FINALIZED --> SALE
    SALE --> [*]
```

# ER Diagram

```mermaid
erDiagram
    USER {
        int id PK
        string username UK
        string name 
        string password
        string role
    }
    PROPERTY {
        int id
        string name
    }
    AGENT {
        int id
        string name
    }
    SALE {
        int id PK
        int property_reservation_id FK
        date sale_date
        decimal final_sale_price
        string commission_details
    }
    PROPERTY_RESERVATION {
        int id PK
        int lead_id FK
        int property_id FK
        date reservation_date
        date expected_closing_date
        decimal reservation_fee
        bool financial_status
        string payment_plan
        decimal loan_amount
        string legal_notes
        bool contract_signed
    }
    LEAD {
        int id PK
        int agent_id FK
        string name
        string contact
        string notes
        string source
        string preferred_property_type
        int status
        date inquiry_date
        decima budget
        string follow_up_status
    }
    LEAD |o--o{ AGENT : has
    PROPERTY_RESERVATION ||--o{ PROPERTY : has
    PROPERTY_RESERVATION ||--o| LEAD : has
    PROPERTY_RESERVATION ||--o| SALE : do
```