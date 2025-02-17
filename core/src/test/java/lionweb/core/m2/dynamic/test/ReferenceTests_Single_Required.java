// Copyright 2024 TRUMPF Laser SE and other contributors
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// SPDX-FileCopyrightText: 2024 TRUMPF Laser SE and other contributors
// SPDX-License-Identifier: Apache-2.0

package lionweb.core.m2.dynamic.test;

import io.lionweb.lioncore.java.model.impl.AbstractClassifierInstance;
import org.junit.Assert;
import org.junit.Test;

public class ReferenceTests_Single_Required extends DynamicNodeTestsBase
{
////    #region Single
//
//    @Test
//    public void Single_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance reference = newLine("myId");
//        parent.addReferenceValue(OffsetDuplicate_source(), reference);
//        Assert.assertNull(reference.getParent());
//        Assert.assertSame(reference, parent.getReferenceValues(OffsetDuplicate_source()));
//    }
//
//    @Test
//    public void Existing_Reflective()
//    {
//        AbstractClassifierInstance oldReference = newLine("old");
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        parent.addReferenceValue(OffsetDuplicate_source(), oldReference);
//        AbstractClassifierInstance reference = newLine("myId");
//        parent.addReferenceValue(OffsetDuplicate_source(), reference);
//        Assert.assertNull(oldReference.getParent());
//        Assert.assertNull(reference.getParent());
//        Assert.assertSame(reference, parent.getReferenceValues(OffsetDuplicate_source()));
//    }
//
////    #endregion
//
////    #region Null
//
//    @Test
//    public void Null_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), null));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//    }
//
////    #endregion
//
////    #region EmptyCollection
//
//    @Test
//    public void EmptyArray_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new DynamicNode[0];
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void EmptyUntypedList_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new ArrayList();
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void EmptyListMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new List<DynamicNode>();
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void EmptySet_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new HashSet<DynamicNode>();
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void EmptyListNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new List<String>();
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
////    #endregion
//
////    #region NullCollection
//
//    @Test
//    public void NullArray_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new DynamicNode[] { null };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void NullUntypedList_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new ArrayList() { null };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void NullListMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new List<DynamicNode>() { null };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void NullListNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new List<String>() { null };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void NullSet_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance values = new HashSet<DynamicNode>() { null };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
////    #endregion
//
////    #region SingleCollection
//
//    @Test
//    public void SingleArray_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newLine("s");
//        AbstractClassifierInstance values = new DynamicNode[] { value };
//
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(value.getParent());
//    }
//
//    @Test
//    public void SingleUntypedArray_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newLine("s");
//        AbstractClassifierInstance values = new Object[] { value };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(value.getParent());
//    }
//
//    @Test
//    public void SingleUntypedList_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newLine("s");
//        AbstractClassifierInstance values = new ArrayList() { value };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(value.getParent());
//    }
//
//    @Test
//    public void SingleListMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newLine("s");
//        AbstractClassifierInstance values = new List<DynamicNode>() { value };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(value.getParent());
//    }
//
//    @Test
//    public void SingleSet_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newLine("s");
//        AbstractClassifierInstance values = new HashSet<DynamicNode>() { value };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(value.getParent());
//    }
//
//    @Test
//    public void SingleListNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newCoord("c");
//        AbstractClassifierInstance values = new List<DynamicNode>() { value };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void SingleUntypedListNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newCoord("c");
//        AbstractClassifierInstance values = new ArrayList() { value };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
//    @Test
//    public void SingleUntypedArrayNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance value = newCoord("c");
//        AbstractClassifierInstance values = new Object[] { value };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//    }
//
////    #endregion
//
////    #region MultipleCollection
//
//    @Test
//    public void MultipleArray_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newLine("sA");
//        AbstractClassifierInstance valueB = newLine("sB");
//        AbstractClassifierInstance values = new DynamicNode[] { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleUntypedArray_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newLine("sA");
//        AbstractClassifierInstance valueB = newLine("sB");
//        AbstractClassifierInstance values = new object[] { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleUntypedList_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newLine("sA");
//        AbstractClassifierInstance valueB = newLine("sB");
//        AbstractClassifierInstance values = new ArrayList() { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleListMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newLine("sA");
//        AbstractClassifierInstance valueB = newLine("sB");
//        AbstractClassifierInstance values = new List<DynamicNode>() { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleSet_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newLine("sA");
//        AbstractClassifierInstance valueB = newLine("sB");
//        AbstractClassifierInstance values = new HashSet<DynamicNode>() { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class, () ->
//            parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleListNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newCoord("cA");
//        AbstractClassifierInstance valueB = newCoord("cB");
//        AbstractClassifierInstance values = new List<DynamicNode>() { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleUntypedListNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newCoord("cA");
//        AbstractClassifierInstance valueB = newCoord("cB");
//        AbstractClassifierInstance values = new ArrayList() { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
//    @Test
//    public void MultipleUntypedArrayNonMatchingType_Reflective()
//    {
//        AbstractClassifierInstance parent = newOffsetDuplicate("od");
//        AbstractClassifierInstance valueA = newCoord("cA");
//        AbstractClassifierInstance valueB = newCoord("cB");
//        AbstractClassifierInstance values = new Object[] { valueA, valueB };
//        Assert.assertThrows(IllegalArgumentException.class,
//            () -> parent.addReferenceValue(OffsetDuplicate_source(), values));
//        Assert.assertThrows(IllegalArgumentException.class, () -> parent.getReferenceValues(OffsetDuplicate_source()));
//        Assert.assertNull(valueA.getParent());
//        Assert.assertNull(valueB.getParent());
//    }
//
////    #endregion
}