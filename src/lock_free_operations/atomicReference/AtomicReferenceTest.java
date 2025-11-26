package lock_free_operations.atomicReference;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        String oldName = "old name";
        String newName = "new name";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);
        atomicReference.set("Unexpected name");

        if (atomicReference.compareAndSet(oldName, newName)) {
            System.out.println("Name updated successfully to: " + atomicReference.get());
        } else {
            System.out.println("Failed to update name. Current name is: " + atomicReference.get());
        }
    }
}

/*
 * CAS - CompareAndSet
 * - Available in all Atomic Class
 * - Compiles into an atomic hardware operation
 * - Many other atomic methods are internally implemented using CAS
 */
