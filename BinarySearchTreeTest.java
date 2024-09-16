package DataStructures;

import Exceptions.*;
import org.junit.Before;
import org.junit.Test;

import ADTs.BinarySearchTreeADT;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

  private BinarySearchTreeADT<Integer> bst;

  @Before
  public void setUp() {
    bst = new BinarySearchTree<>();
  }

  @Test
  public void testInsertSingleElement() {
    bst.insert(5);
    assertTrue(bst.contains(5));
  }

  @Test
  public void testInsertMultipleElements() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);

    assertTrue(bst.contains(10));
    assertTrue(bst.contains(5));
    assertTrue(bst.contains(15));
  }

  @Test
  public void testInsertDuplicatesAndRemove() {
    bst.insert(7);
    bst.insert(7);

    assertTrue(bst.contains(7));
    bst.delete(7);
    assertTrue(bst.contains(7));
    bst.delete(7);
    try {
      assertFalse(bst.contains(7));
      fail("EmptyTreeException should be thrown");
    } catch (EmptyTreeException e) {
      // Do nothing, this is expected
    }
  }

  @Test
  public void testInsertNegativeNumbers() {
    bst.insert(-3);
    bst.insert(2);
    bst.insert(-1);

    assertTrue(bst.contains(-3));
    assertTrue(bst.contains(2));
    assertTrue(bst.contains(-1));
  }

  @Test
  public void testInsertAndSearch() {
    bst.insert(4);
    bst.insert(2);
    bst.insert(6);

    try {
      assertEquals(Integer.valueOf(4), bst.search(4));
      assertEquals(Integer.valueOf(2), bst.search(2));
      assertEquals(Integer.valueOf(6), bst.search(6));
    } catch (NodeNotFoundException e) {
      fail("NodeNotFoundException should not be thrown");
    }
  }

  @Test
  public void testInsertAndSize() {
    bst.insert(1);
    bst.insert(2);
    bst.insert(3);

    assertEquals(3, bst.size());
  }

  @Test(expected = EmptyTreeException.class)
  public void testContainsEmptyTree() throws EmptyTreeException {
    assertFalse(bst.contains(5));
  }

  @Test(expected = EmptyTreeException.class)
  public void testSearchEmptyTree() throws NodeNotFoundException, EmptyTreeException {
    bst.search(5);
  }

  @Test
  public void testContainsElementInTree() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);

    try {
      assertTrue(bst.contains(5));
    } catch (EmptyTreeException e) {
      fail("EmptyTreeException should not be thrown");
    }
  }

  @Test
  public void testSearchElementInTree() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);

    try {
      assertEquals(Integer.valueOf(5), bst.search(5));
    } catch (NodeNotFoundException | EmptyTreeException e) {
      fail("NodeNotFoundException and EmptyTreeException should not be thrown");
    }
  }

  @Test
  public void testContainsElementNotInTree() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);
    assertFalse(bst.contains(20));
  }

  @Test(expected = NodeNotFoundException.class)
  public void testSearchElementNotInTree() throws NodeNotFoundException {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);
    bst.search(20);
  }

  @Test(expected = EmptyTreeException.class)
  public void testDeleteEmptyTree() throws EmptyTreeException {
    bst.delete(5);
  }

  @Test
  public void testDeleteSingleNode() {
    bst.insert(10);

    try {
      bst.delete(10);
    } catch (EmptyTreeException e) {
      fail("EmptyTreeException should not be thrown");
    }

    try {
      assertFalse(bst.contains(10));
      fail("EmptyTreeException should be thrown");
    } catch (EmptyTreeException e) {
      // Do nothing, this is expected
    }
  }

  @Test
  public void testDeleteNodeWithMultipleElements() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);
    bst.insert(3);
    bst.insert(7);

    try {
      bst.delete(5);
      assertFalse(bst.contains(5));
    } catch (EmptyTreeException e) {
      fail("EmptyTreeException should not be thrown");
    }
  }

  @Test
  public void testDeleteElementNotInTree() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(15);

    try {
      bst.delete(20);
      assertFalse(bst.contains(20));
    } catch (EmptyTreeException e) {
      fail("EmptyTreeException should not be thrown");
    }
  }

  @Test
  public void testDeleteDuplicateElements() {
    bst.insert(10);
    bst.insert(5);
    bst.insert(5);
    bst.insert(15);

    try {
      bst.delete(5);
      assertTrue(bst.contains(5));
    } catch (EmptyTreeException e) {
      fail("EmptyTreeException should not be thrown");
    }
  }

  @Test
  public void testTreeTraversals() {
    // Test Case 1: Insert 5, 3, 7, 2, 4, 6, 8
    bst.insert(5);
    bst.insert(3);
    bst.insert(7);
    bst.insert(2);
    bst.insert(4);
    bst.insert(6);
    bst.insert(8);

    assertEquals("[2, 3, 4, 5, 6, 7, 8]", bst.inOrder());
    assertEquals("[5, 3, 2, 4, 7, 6, 8]", bst.preOrder());
    assertEquals("[2, 4, 3, 6, 8, 7, 5]", bst.postOrder());

    // Test Case 2: Insert 40, 20, 60, 10, 30, 50, 70
    bst = new BinarySearchTree<>();
    bst.insert(40);
    bst.insert(20);
    bst.insert(60);
    bst.insert(10);
    bst.insert(30);
    bst.insert(50);
    bst.insert(70);

    assertEquals("[10, 20, 30, 40, 50, 60, 70]", bst.inOrder());
    assertEquals("[40, 20, 10, 30, 60, 50, 70]", bst.preOrder());
    assertEquals("[10, 30, 20, 50, 70, 60, 40]", bst.postOrder());
  }
}
