package com.app.organizers;

import java.util.ArrayList;
import java.util.List;

public class OrganizerCommission {
    
    private String commissionName;
    private List<CommissionMember> members;

    public OrganizerCommission(String commissionName) {
        this.commissionName = commissionName;
        this.members = new ArrayList<>();
    }

    public void addMember(CommissionMember member) {
        this.members.add(member);
        System.out.println("[COMISIÓN] " + member.getName() + " ha sido añadido/a a la " + this.commissionName);
    }

    public void displayMembers() {
        System.out.println("---- Miembros de la " + this.commissionName + " ----");
        if (members.isEmpty()) {
            System.out.println("No hay miembros registrados en esta comisión.");
        } else {
            for (CommissionMember member : members) {
                System.out.println("- " + member.getName() + " (Rol: " + member.getCommissionRole() + ")");
            }
        }
        System.out.println("----------------------------------------------");
    }

    public List<CommissionMember> getMembers() {
        return members;
    }
}
