# **1. Devices Needed in Packet Tracer**

* **1 Edge Router** – connects to Internet/cloud
* **1 Layer 3 Core Switch** – inter-VLAN routing and default gateways
* **4 Access Switches** – 2 per floor, connected to user devices
* **4 Wireless Access Points** – as per your table
* **Servers** – File, Email, DNS (static IPs)
* **End Devices** – PCs, laptops, for each user group
* **Cloud/Internet** – optional for external connectivity

---

# **2. Network Layout**

**Floor 1**

* Access Switch 1 → Operations & Logistics PCs
* Access Switch 2 → Guest devices, Reception Wi-Fi AP
* AP1 → VLAN 40 (employee Wi-Fi)
* AP2 → VLAN 40 (Guest Wi-Fi)

**Floor 2**

* Access Switch 3 → Finance, HR PCs
* Access Switch 4 → Management PCs
* AP3 → VLAN 40 (employee Wi-Fi)
* AP4 → VLAN 40 (employee/meeting room Wi-Fi)

**Server Room (Central)**

* Core Layer 3 Switch → Inter-VLAN Routing
* File Server → 192.168.70.10
* Email Server → 192.168.70.20
* DNS Server → 192.168.70.30
* Router → connects to Internet
* Firewall → optional between router and core switch

---

# **3. VLAN Configuration (Layer 3 Switch)**

```plaintext
! Create VLANs
vlan 10
 name Operations
vlan 20
 name Finance
vlan 30
 name HR
vlan 40
 name IT_Admin
vlan 50
 name Management
vlan 60
 name Guest
vlan 70
 name Servers

! Assign VLAN interfaces (SVI) with IP addresses
interface Vlan10
 ip address 192.168.10.1 255.255.255.0
 no shutdown

interface Vlan20
 ip address 192.168.20.1 255.255.255.0
 no shutdown

interface Vlan30
 ip address 192.168.30.1 255.255.255.0
 no shutdown

interface Vlan40
 ip address 192.168.40.1 255.255.255.0
 no shutdown

interface Vlan50
 ip address 192.168.50.1 255.255.255.0
 no shutdown

interface Vlan60
 ip address 192.168.60.1 255.255.255.0
 no shutdown

interface Vlan70
 ip address 192.168.70.1 255.255.255.0
 no shutdown
```

---

# **4. Assign Access Switch Ports to VLANs**

```plaintext
! Example: Operations PCs (Floor 1)
interface range GigabitEthernet1/0/1-10
 switchport mode access
 switchport access vlan 10

! Finance PCs (Floor 2)
interface range GigabitEthernet2/0/1-10
 switchport mode access
 switchport access vlan 20

! Guest Wi-Fi / AP trunk (VLAN 40 for AP management + VLAN 60 for guests)
interface GigabitEthernet1/0/24
 switchport mode trunk
 switchport trunk allowed vlan 40,60
```

> Repeat for each access switch, mapping ports to the correct VLANs.

---

# **5. Configure Servers (Static IPs)**

**Example in Packet Tracer:**

| Server       | VLAN | IP Address    | Default Gateway | DNS           |
| ------------ | ---- | ------------- | --------------- | ------------- |
| File Server  | 70   | 192.168.70.10 | 192.168.70.1    | 192.168.70.30 |
| Email Server | 70   | 192.168.70.20 | 192.168.70.1    | 192.168.70.30 |
| DNS Server   | 70   | 192.168.70.30 | 192.168.70.1    | 192.168.70.30 |

---

# **6. Configure Wireless Access Points**

**Example for AP1 – Employee Wi-Fi (VLAN 40)**

```plaintext
! Assign management IP (static) in VLAN 40
IP: 192.168.40.50
Subnet: 255.255.255.0
Gateway: 192.168.40.1

! Set SSID and VLAN mapping
SSID: Staff_WiFi
VLAN: 40

! Optional: Guest SSID
SSID: Guest_WiFi
VLAN: 60
```

> Repeat for AP2–AP4 with the IPs from your table.

---

# **7. Router Configuration (Edge)**

```plaintext
! Connect to ISP
interface GigabitEthernet0/0
 ip address dhcp
 no shutdown

! Connect to Core Switch
interface GigabitEthernet0/1
 ip address 192.168.70.254 255.255.255.0
 no shutdown

! Default route to ISP
ip route 0.0.0.0 0.0.0.0 dhcp
```

---


# **APL Logistics 2-Floor Office – Packet Tracer Topology**

## **1. Devices**

| Device Type         | Quantity | Notes                                          |
| ------------------- | -------- | ---------------------------------------------- |
| Layer 3 Core Switch | 1        | Central switch, inter-VLAN routing             |
| Access Switch       | 4        | 2 per floor for wired clients                  |
| Router (Edge)       | 1        | Connects office to Internet                    |
| Wireless AP         | 4        | VLAN 40 for employees, VLAN 60 for guest Wi-Fi |
| File Server         | 1        | 192.168.70.10                                  |
| Email Server        | 1        | 192.168.70.20                                  |
| DNS Server          | 1        | 192.168.70.30                                  |
| PCs / Laptops       | 50+      | Approximate users per VLAN, see user groups    |
| Cloud / Internet    | 1        | Optional                                       |

---

## **2. Physical / Logical Layout**

**Server Room (Central)**

* Layer 3 Core Switch (SVIs for VLANs 10–70)
* File, Email, DNS Servers connected directly to Core Switch
* Edge Router connected to Core Switch → Internet

**Floor 1**

* Access Switch 1 → Operations & Logistics PCs (VLAN 10)
* Access Switch 2 → Guest PCs and APs (VLAN 60)
* AP1 → Staff Wi-Fi (VLAN 40)
* AP2 → Guest Wi-Fi (VLAN 60)

**Floor 2**

* Access Switch 3 → Finance PCs (VLAN 20) and HR PCs (VLAN 30)
* Access Switch 4 → Management PCs (VLAN 50)
* AP3 → Staff Wi-Fi (VLAN 40)
* AP4 → Meeting Room Wi-Fi (VLAN 40)

---

## **3. VLAN Mapping**

| VLAN | Purpose                   | IP Subnet       | Default Gateway | Devices                  |
| ---- | ------------------------- | --------------- | --------------- | ------------------------ |
| 10   | Operations & Logistics    | 192.168.10.0/24 | 192.168.10.1    | PCs (Floor 1)            |
| 20   | Finance                   | 192.168.20.0/24 | 192.168.20.1    | PCs (Floor 2)            |
| 30   | HR                        | 192.168.30.0/24 | 192.168.30.1    | PCs (Floor 2)            |
| 40   | IT/Admin + Employee Wi-Fi | 192.168.40.0/24 | 192.168.40.1    | APs, IT Staff            |
| 50   | Management                | 192.168.50.0/24 | 192.168.50.1    | PCs (Floor 2)            |
| 60   | Guest Wi-Fi               | 192.168.60.0/24 | 192.168.60.1    | APs, Guest Devices       |
| 70   | Servers                   | 192.168.70.0/24 | 192.168.70.1    | File, Email, DNS Servers |

---

## **4. Device Connections**

* **Router → Core Switch**: GigabitEthernet connection
* **Core Switch → Access Switches**: GigabitEthernet trunk links carrying all VLANs
* **Access Switch → PCs**: Access ports assigned to correct VLAN
* **Access Switch → Wireless APs**: Trunk port carrying VLAN 40 + VLAN 60
* **Servers → Core Switch**: Access ports in VLAN 70

---

## **5. Sample IP Assignments for APs**

| AP  | Location                    | IP Address    | VLAN |
| --- | --------------------------- | ------------- | ---- |
| AP1 | Floor 1 – Operations        | 192.168.40.50 | 40   |
| AP2 | Floor 1 – Reception (Guest) | 192.168.40.51 | 40   |
| AP3 | Floor 2 – Finance/HR        | 192.168.40.52 | 40   |
| AP4 | Floor 2 – Meeting Room      | 192.168.40.53 | 40   |

---

## **6. End Devices / PCs Approximate Count**

| VLAN | User Group             | Approx. PCs / Laptops |
| ---- | ---------------------- | --------------------- |
| 10   | Operations & Logistics | 25                    |
| 20   | Finance                | 10                    |
| 30   | HR                     | 5                     |
| 40   | IT/Admin               | 4                     |
| 50   | Management             | 8                     |
| 60   | Guests                 | Variable              |

---

## **7. How to Build in Packet Tracer**

1. Place the **router** and **core switch** in the central server room.
2. Connect **access switches** per floor to the core switch with **trunk links**.
3. Assign **PCs to access switch ports** in the correct VLAN.
4. Connect **servers directly to the core switch** in VLAN 70.
5. Add **wireless APs**, set static management IPs in VLAN 40, map SSID to correct VLANs.
6. Configure **Layer 3 switch SVIs** for each VLAN with IP addresses as default gateways.
7. Configure **router to provide Internet access** (optional cloud in Packet Tracer).
8. Test connectivity: PCs → Gateway → Servers → AP Wi-Fi → Internet.

---


## **APL Logistics – 2-Floor Office Network Diagram (Packet Tracer Blueprint)**

```
                           ┌──────────────┐
                           │   Internet   │
                           │    (Cloud)   │
                           └──────┬───────┘
                                  │
                         ┌────────▼────────┐
                         │   Edge Router   │
                         │   (ISP / NAT)   │
                         └────────┬────────┘
                                  │
                   ┌──────────────▼──────────────┐
                   │    Layer 3 Core Switch       │
                   │  (Inter-VLAN Routing / SVI) │
                   │                              │
                   │ VLAN 10 → 192.168.10.1       │
                   │ VLAN 20 → 192.168.20.1       │
                   │ VLAN 30 → 192.168.30.1       │
                   │ VLAN 40 → 192.168.40.1       │
                   │ VLAN 50 → 192.168.50.1       │
                   │ VLAN 60 → 192.168.60.1       │
                   │ VLAN 70 → 192.168.70.1       │
                   └───────┬───────────┬─────────┘
                           │           │
          ┌────────────────┘           └────────────────┐
          │                                               │
```

---

## **Server Room (Connected to Core Switch)**

```
        ┌──────────────────────────┐
        │        Servers VLAN 70   │
        │                          │
        │ File Server  192.168.70.10
        │ Email Server 192.168.70.20
        │ DNS Server   192.168.70.30
        │                          │
        └──────────────────────────┘
```

---

## **Floor 1 – Operations & Guest Area**

```
     ┌──────────────────────────┐
     │     Access Switch F1-A   │  (TRUNK to Core)
     └───────┬───────────┬─────┘
             │           │
     VLAN 10 │           │ VLAN 10
   Operations│           │ Operations
        PCs  │           │ PCs
```

```
     ┌──────────────────────────┐
     │     Access Switch F1-B   │  (TRUNK to Core)
     └───────┬───────────┬─────┘
             │           │
         AP1 │           │ AP2
 (Staff WiFi)│     (Guest WiFi)
 192.168.40.50     192.168.40.51

 SSID: Staff_WiFi → VLAN 40
 SSID: Guest_WiFi → VLAN 60
```

---

## **Floor 2 – Finance, HR, Management**

```
     ┌──────────────────────────┐
     │     Access Switch F2-A   │  (TRUNK to Core)
     └───────┬───────────┬─────┘
             │           │
        VLAN 20       VLAN 30
       Finance PCs     HR PCs
```

```
     ┌──────────────────────────┐
     │     Access Switch F2-B   │  (TRUNK to Core)
     └───────┬───────────┬─────┘
             │           │
        VLAN 50         AP3 / AP4
     Management PCs   Staff WiFi
                     192.168.40.52
                     192.168.40.53
```

---

## **Wireless Summary**

```
Staff WiFi (SSID: Staff_WiFi)
 → VLAN 40 (IT Administration)

Guest WiFi (SSID: Guest_WiFi)
 → VLAN 60 (Internet only)
```



